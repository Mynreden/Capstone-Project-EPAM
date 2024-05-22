package com.example.capstoneproject.controllers.frontend;

import com.example.capstoneproject.DTO.CartItemDTO;
import com.example.capstoneproject.domain.Cart;
import com.example.capstoneproject.domain.User;
import com.example.capstoneproject.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @PostMapping("/addproduct")
    public RedirectView addProduct(@RequestParam Long productVariantId,
                                   HttpSession session,
                                   RedirectAttributes redirectAttributes,
                                   HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        if (user == null){
            redirectAttributes.addFlashAttribute("message", "You need to login before making order");
            return new RedirectView("/sign_in");
        }
        Optional<Cart> optionalCart = cartService.getCartById(user.getId());
        if (optionalCart.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Something went wrong");
            return new RedirectView("/product/" + productVariantId);
        }
        Cart cart = optionalCart.get();
        optionalCart = cartService.addCartItems(cart, productVariantId, 1);
        if (optionalCart.isEmpty()){
            redirectAttributes.addFlashAttribute("message", "Something went wrong");
            String referer = request.getHeader("referer");
            return new RedirectView(referer);
        }
        redirectAttributes.addFlashAttribute("message", "Product added successfully");

        String referer = request.getHeader("referer");
        return new RedirectView(referer);
    }

    @PutMapping("/remove/{productVariantId}")
    public ResponseEntity<String> reduceProductAmountFromCart(@PathVariable Long productVariantId,
                                                              HttpSession session){
        return changeProductAmountFromCart(productVariantId, session, -1);
    }

    @PutMapping("/add/{productVariantId}")
    public ResponseEntity<String> increaseProductAmountFromCart(@PathVariable Long productVariantId,
                                                              HttpSession session){
        return changeProductAmountFromCart(productVariantId, session, 1);
    }

    private ResponseEntity<String> changeProductAmountFromCart(@PathVariable Long productVariantId,
                                     HttpSession session,
                                     int action){
        User user = (User) session.getAttribute("user");
        if (user == null){
            return new ResponseEntity<>("You should authenticate", HttpStatus.UNAUTHORIZED);
        }
        Optional<Cart> optionalCart = cartService.getCartById(user.getId());
        if (optionalCart.isEmpty()){
            return new ResponseEntity<>("Server error occurred, please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Cart cart = optionalCart.get();
        optionalCart = cartService.changeProductAmountInCart(cart, productVariantId, action);
        if (optionalCart.isEmpty()){
            return new ResponseEntity<>("Server error occurred, please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Product amount changed", HttpStatus.OK);

    }


}
