package com.example.capstoneproject.services;

import com.example.capstoneproject.domain.Cart;
import com.example.capstoneproject.domain.CartItem;
import com.example.capstoneproject.domain.ProductVariant;
import com.example.capstoneproject.repositories.CartItemRepository;
import com.example.capstoneproject.repositories.CartRepository;
import com.example.capstoneproject.repositories.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductVariantRepository productVariantRepository;

    @Autowired
    public CartService(CartRepository cartRepository,
                       CartItemRepository cartItemRepository,
                       ProductVariantRepository productVariantRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productVariantRepository = productVariantRepository;
    }

    public Optional<Cart> getCartById(Long id) {
        try {
            Optional<Cart> optionalCart = cartRepository.findById(id);
            if (optionalCart.isEmpty()){
                return Optional.of(cartRepository.save(new Cart(null, 0, new ArrayList<>())));
            }
            return optionalCart;
        } catch (Throwable error){
            return Optional.empty();
        }

    }

    public Optional<Cart> addCartItems(Long cartId, Long productVariantId, int quantity){
        try {
            Optional<Cart> optionalCart = cartRepository.findById(cartId);
            if (optionalCart.isEmpty()){
                return Optional.empty();
            }
            Cart cart = optionalCart.get();
            for (CartItem cartItem : cart.getCartItems()){
                if (cartItem.getProductVariant().getId().equals(productVariantId)){
                    return changeProductAmountInCart(cart, productVariantId, 1);
                }
            }
            Optional<ProductVariant> optionalProductVariant = productVariantRepository.findById(productVariantId);
            if (optionalProductVariant.isEmpty()){
                return Optional.empty();
            }
            ProductVariant productVariant = optionalProductVariant.get();
            List<CartItem> cartItems = cart.getCartItems();
            CartItem cartItem = new CartItem(null, productVariant, (long) quantity, cart);
            cartItems.add(cartItem);
            cart.setCartItems(cartItems);
            cart.setTotalPrice(cart.getTotalPrice() + productVariant.getPrice());

            cartRepository.save(cart);
            return cartRepository.findById(cartId);
        } catch (Throwable error){
            return Optional.empty();
        }
    }

    public Optional<Cart> changeProductAmountInCart(Cart cart, Long productVariantId, int action){
        try {
            for (CartItem cartItem : cart.getCartItems()){
                if (cartItem.getProductVariant().getId().equals(productVariantId)){
                    cartItem.setQuantity(cartItem.getQuantity() + action);
                    if (cartItem.getQuantity() == 0){
                        List<CartItem> cartItems = cart.getCartItems();
                        cartItems.remove(cartItem);
                        cartItemRepository.delete(cartItem);
                        cart.setCartItems(cartItems);
                    }
                    cart.setTotalPrice(cart.getTotalPrice() + cartItem.getProductVariant().getPrice() * action);
                    return Optional.of(cartRepository.save(cart));
                }
            }
            return Optional.empty();
        } catch (Throwable error){
            System.out.println(error.getMessage());
            return Optional.empty();
        }
    }

}
