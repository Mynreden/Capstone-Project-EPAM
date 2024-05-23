package com.example.capstoneproject.controllers;

import com.example.capstoneproject.domain.*;
import com.example.capstoneproject.services.CartService;
import com.example.capstoneproject.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final CartService cartService;

    @Autowired
    public OrderController(OrderService orderService,
                           CartService cartService){
        this.orderService = orderService;
        this.cartService = cartService;
    }

    @PostMapping("/create")
    public RedirectView addProduct(@RequestParam String city,
                                   @RequestParam String street,
                                   @RequestParam String house,
                                   @RequestParam String apartment,
                                   HttpSession session,
                                   RedirectAttributes redirectAttributes,
                                   HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        Optional<Address> optionalAddress = orderService.createAddress(city, street, house, apartment);
        if (optionalAddress.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Internal Error occurred. Please try again");
            return new RedirectView("/cart");
        }
        orderService.createOrder(user.getId(), optionalAddress.get().getId());
        return new RedirectView("/orders");
    }

    @GetMapping()
    public String renderOrdersPage(HttpSession session,
                                   RedirectAttributes redirectAttributes,
                                   Model model){
        User user = (User) session.getAttribute("user");
        if (user == null){
            redirectAttributes.addFlashAttribute("message", "You must be logged in to see orders.");
            return "redirect:/sign_in";
        }
        List<Order> orders = orderService.findOrdersByUserId(user.getId());
        for (Order order : orders) {
            for (OrderItem orderItem : order.getOrderItems()){
                orderItem.getProductVariant().getProduct().getImages();
            }
            System.out.println(order.getOrderItems().size());
        }
        model.addAttribute("orders", orders);
        model.addAttribute("user", user);
        return "orders";
    }

    @GetMapping("/new")
    public String renderOrderCreatingPage(HttpSession session,
                                   RedirectAttributes redirectAttributes,
                                   Model model){
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
        model.addAttribute("cart", cart);
        model.addAttribute("user", user);
        return "order_creating";
    }


}