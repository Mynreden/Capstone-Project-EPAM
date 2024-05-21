package com.example.capstoneproject.controllers.frontend;

import com.example.capstoneproject.domain.Product;
import com.example.capstoneproject.domain.ProductVariant;
import com.example.capstoneproject.domain.User;
import com.example.capstoneproject.services.ProductService;
import com.example.capstoneproject.services.interfaces.ProductServiceInterface;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


@Controller
public class FrontendController {
    private final ProductServiceInterface productService;

    @Autowired
    public FrontendController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/")
    public String renderHomePage(Model model,
                                 HttpSession session) {
        List<Product> products = productService.getAllProducts();
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
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
        model.addAttribute(product);
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "details";
    }

    @GetMapping("/sign_in")
    public String renderSignPage(Model model,
                                 HttpSession session) {
        return "sign_in";
    }
}