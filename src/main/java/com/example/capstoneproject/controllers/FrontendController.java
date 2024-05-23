package com.example.capstoneproject.controllers;

import com.example.capstoneproject.domain.Cart;
import com.example.capstoneproject.domain.CartItem;
import com.example.capstoneproject.domain.Product;
import com.example.capstoneproject.domain.User;
import com.example.capstoneproject.services.CartService;
import com.example.capstoneproject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


@Controller
public class FrontendController {
    private final ProductService productService;
    private final CartService cartService;

    @Autowired
    public FrontendController(ProductService productService, CartService cartService){
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String renderHomePage(Model model,
                                 HttpSession session) {
        List<Product> products = productService.getAllProducts();
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        for (Product product : products){
            product.getImages(); // unpacking
        }
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/product/{id}")
    public String renderProductPage(@PathVariable Long id,
                                    Model model,
                                    HttpSession session) {
        Optional<Product> optionalProduct = productService.getProductById(id);
        if (optionalProduct.isEmpty()){
            return "error";
        }
        Product product = optionalProduct.get();
        product.getImages(); // unpacking
        model.addAttribute(product);
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "details";
    }

    @GetMapping("/sign_in")
    public String renderSignPage(Model model,
                                 HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null){
            return "redirect:/";
        }
        return "sign_in";
    }

    @GetMapping("/cart")
    public String renderCartPage(Model model,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        if (user == null){
            redirectAttributes.addFlashAttribute("message", "Please sign in to view your cart.");
            return "redirect:/sign_in";
        }

        Optional<Cart> optionalCart = cartService.getCartById(user.getId());
        if (optionalCart.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Internal error occurred");
            return "redirect:/";
        }
        Cart cart = optionalCart.get();
        for (CartItem cartItem : cart.getCartItems()){
            cartItem.getProductVariant().getProduct().getImages();
        }
        model.addAttribute("cart", optionalCart.get());
        model.addAttribute("user", user);
        return "cart";
    }
}