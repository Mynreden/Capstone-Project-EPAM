package com.example.capstoneproject.services;

import com.example.capstoneproject.domain.Cart;
import com.example.capstoneproject.domain.CartItem;
import com.example.capstoneproject.domain.ProductVariant;
import com.example.capstoneproject.repositories.CartItemRepository;
import com.example.capstoneproject.repositories.CartRepository;
import com.example.capstoneproject.repositories.ProductVariantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private ProductVariantRepository productVariantRepository;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCartById_CartExists() {
        Cart cart = new Cart();
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));

        Optional<Cart> result = cartService.getCartById(1L);

        assertTrue(result.isPresent());
        assertEquals(cart, result.get());
        verify(cartRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetCartById_CartDoesNotExist() {
        when(cartRepository.findById(1L)).thenReturn(Optional.empty());
        Cart newCart = new Cart(1L, 0, new ArrayList<>());
        when(cartRepository.save(any(Cart.class))).thenReturn(newCart);

        Optional<Cart> result = cartService.getCartById(1L);

        assertTrue(result.isPresent());
        assertNotNull(result.get().getId());
        verify(cartRepository, times(1)).findById(1L);
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    public void testAddCartItems_ProductVariantExists() {
        Cart cart = new Cart();
        cart.setTotalPrice(0L);
        cart.setCartItems(new ArrayList<>());
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));

        ProductVariant productVariant = new ProductVariant();
        productVariant.setId(1L);
        productVariant.setPrice(100L);
        when(productVariantRepository.findById(1L)).thenReturn(Optional.of(productVariant));

        CartItem cartItem = new CartItem(null, productVariant, 2L, cart);
        cart.getCartItems().add(cartItem);
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        Optional<Cart> result = cartService.addCartItems(1L, 1L, 2);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getCartItems().size());
        assertEquals(3L, result.get().getCartItems().get(0).getQuantity());
        verify(cartRepository, times(1)).findById(1L);
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    public void testAddCartItems_ProductVariantDoesNotExist() {
        Cart cart = new Cart();
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));
        when(productVariantRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Cart> result = cartService.addCartItems(1L, 1L, 2);

        assertFalse(result.isPresent());
        verify(cartRepository, times(1)).findById(1L);
    }

    @Test
    public void testChangeProductAmountInCart_ProductInCart() {
        Cart cart = new Cart();
        cart.setTotalPrice(100L);
        ProductVariant productVariant = new ProductVariant();
        productVariant.setId(1L);
        productVariant.setPrice(100L);

        CartItem cartItem = new CartItem(null, productVariant, 2L, cart);
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);
        cart.setCartItems(cartItems);

        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        Optional<Cart> result = cartService.changeProductAmountInCart(cart, 1L, 1);

        assertTrue(result.isPresent());
        assertEquals(3L, result.get().getCartItems().get(0).getQuantity());
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    public void testChangeProductAmountInCart_ProductNotInCart() {
        Cart cart = new Cart();
        ProductVariant productVariant = new ProductVariant();
        productVariant.setId(1L);
        productVariant.setPrice(100L);

        CartItem cartItem = new CartItem(null, productVariant, 2L, cart);
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);
        cart.setCartItems(cartItems);

        Optional<Cart> result = cartService.changeProductAmountInCart(cart, 2L, 1);

        assertFalse(result.isPresent());
    }

    @Test
    public void testChangeProductAmountInCart_RemoveProductWhenQuantityIsZero() {
        Cart cart = new Cart();
        cart.setTotalPrice(100L);
        ProductVariant productVariant = new ProductVariant();
        productVariant.setId(1L);
        productVariant.setPrice(100L);

        CartItem cartItem = new CartItem(null, productVariant, 1L, cart);
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);
        cart.setCartItems(cartItems);

        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        Optional<Cart> result = cartService.changeProductAmountInCart(cart, 1L, -1);

        assertTrue(result.isPresent());
        assertTrue(result.get().getCartItems().isEmpty());
        verify(cartItemRepository, times(1)).delete(cartItem);
        verify(cartRepository, times(1)).save(any(Cart.class));
    }
}
