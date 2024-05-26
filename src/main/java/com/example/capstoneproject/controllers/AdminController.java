package com.example.capstoneproject.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.capstoneproject.domain.*;
import com.example.capstoneproject.forms.ProductForm;
import com.example.capstoneproject.forms.ProductVariantForm;
import com.example.capstoneproject.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ProductService productService;
    private final CartService cartService;
    private final UserService userService;
    private final OrderService orderService;
    private final CategoryService categoryService;
    private Cloudinary cloudinary;


    @Autowired
    public AdminController(ProductService productService,
                           CartService cartService,
                           UserService userService,
                           OrderService orderService,
                           CategoryService categoryService,
                           Cloudinary cloudinary){
        this.productService = productService;
        this.cartService = cartService;
        this.userService = userService;
        this.orderService = orderService;
        this.categoryService = categoryService;
        this.cloudinary = cloudinary;
    }

    @GetMapping
    public String renderHomePage(Model model,
                                 HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        List<Order> orders = orderService.getOrders();
        model.addAttribute("orders", orders);

        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "admin";
    }

    @PostMapping("/orders/updateStatus")
    public String updateOrderStatus(@RequestParam Long orderId, @RequestParam String status) {
        orderService.updateOrderStatus(orderId, status);
        return "redirect:/admin";
    }

    @PostMapping("/products/new")
    public String addProduct(@ModelAttribute ProductForm productForm,
                             @RequestParam("images") MultipartFile[] files) {
        Product product = new Product();
        product.setName(productForm.getName());
        product.setDescription(productForm.getDescription());

        Category category;
        Optional<Category> optionalCategory = categoryService.getCategoryByName(productForm.getCategoryName());
        if (optionalCategory.isPresent()) {
            category = optionalCategory.get();
        } else {
            category = new Category();
            category.setName(productForm.getCategoryName());
            category.setDescription(productForm.getCategoryDescription());
            category = categoryService.save(category);
        }
        System.out.println(category.getId() + category.getName());
        product.setCategory(category);

        List<ProductVariant> variants = new ArrayList<>();
        for (ProductVariantForm variantForm : productForm.getVariants()) {
            ProductVariant variant = new ProductVariant();
            variant.setColor(variantForm.getColor());
            variant.setSize(variantForm.getSize());
            variant.setPrice(variantForm.getPrice());
            variant.setProduct(product);
            variants.add(variant);
        }
        product.setProductVariants(variants);

        List<Image> images = new ArrayList<>();
        for (MultipartFile file : files) {
            Map<?, ?> options = ObjectUtils.asMap("folder", "img");
            Map<?, ?> uploadResult = null;
            try {
                uploadResult = cloudinary.uploader().upload(file.getBytes(), options);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            images.add(new Image(null, (String) uploadResult.get("url"), product));
        }
        product.setImages(images);

        productService.saveProduct(product);
        return "redirect:/admin";
    }

}