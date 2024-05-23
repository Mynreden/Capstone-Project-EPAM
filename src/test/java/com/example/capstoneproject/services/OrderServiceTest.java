package com.example.capstoneproject.services;

import com.example.capstoneproject.domain.*;
import com.example.capstoneproject.repositories.AddressRepository;
import com.example.capstoneproject.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CartService cartService;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindOrdersByUserId_Success() {
        Order order1 = new Order();
        Order order2 = new Order();
        List<Order> orders = List.of(order1, order2);

        when(orderRepository.findAllByUserId(1L)).thenReturn(orders);

        List<Order> result = orderService.findOrdersByUserId(1L);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(orderRepository, times(1)).findAllByUserId(1L);
    }

    @Test
    public void testFindOrdersByUserId_Empty() {
        when(orderRepository.findAllByUserId(1L)).thenReturn(List.of());

        List<Order> result = orderService.findOrdersByUserId(1L);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(orderRepository, times(1)).findAllByUserId(1L);
    }

    @Test
    public void testFindOrdersByUserId_Exception() {
        when(orderRepository.findAllByUserId(1L)).thenThrow(new RuntimeException());

        List<Order> result = orderService.findOrdersByUserId(1L);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(orderRepository, times(1)).findAllByUserId(1L);
    }

    @Test
    public void testCreateOrder_Success() {
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(2L);
        List<CartItem> cartItems = List.of(cartItem);
        Cart cart = new Cart();
        cart.setCartItems(cartItems);
        cart.setTotalPrice(100L);

        User user = new User();
        Address address = new Address();

        when(cartService.getCartById(1L)).thenReturn(Optional.of(cart));
        when(userService.findById(1L)).thenReturn(Optional.of(user));
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        when(orderRepository.save(any(Order.class))).thenAnswer(i -> i.getArguments()[0]);

        Optional<Order> result = orderService.createOrder(1L, 1L);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getOrderItems().size());
        verify(cartService, times(1)).getCartById(1L);
        verify(userService, times(1)).findById(1L);
        verify(addressRepository, times(1)).findById(1L);
        verify(orderRepository, times(2)).save(any(Order.class));
    }

    @Test
    public void testCreateOrder_NoCart() {
        when(cartService.getCartById(1L)).thenReturn(Optional.empty());

        Optional<Order> result = orderService.createOrder(1L, 1L);

        assertFalse(result.isPresent());
        verify(cartService, times(1)).getCartById(1L);
    }

    @Test
    public void testCreateOrder_NoUser() {
        Cart cart = new Cart();

        when(cartService.getCartById(1L)).thenReturn(Optional.of(cart));
        when(userService.findById(1L)).thenReturn(Optional.empty());

        Optional<Order> result = orderService.createOrder(1L, 1L);

        assertFalse(result.isPresent());
        verify(cartService, times(1)).getCartById(1L);
        verify(userService, times(1)).findById(1L);
    }

    @Test
    public void testCreateOrder_NoAddress() {
        Cart cart = new Cart();
        User user = new User();

        when(cartService.getCartById(1L)).thenReturn(Optional.of(cart));
        when(userService.findById(1L)).thenReturn(Optional.of(user));
        when(addressRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Order> result = orderService.createOrder(1L, 1L);

        assertFalse(result.isPresent());
        verify(cartService, times(1)).getCartById(1L);
        verify(userService, times(1)).findById(1L);
        verify(addressRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateAddress_Success() {
        Address address = new Address(null, "City", "Street", "House", "Apartment");

        when(addressRepository.save(any(Address.class))).thenAnswer(i -> i.getArguments()[0]);

        Optional<Address> result = orderService.createAddress("City", "Street", "House", "Apartment");

        assertTrue(result.isPresent());
        assertEquals("City", result.get().getCity());
        verify(addressRepository, times(1)).save(any(Address.class));
    }

    @Test
    public void testGetAddressById_Success() {
        Address address = new Address();

        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        Optional<Address> result = orderService.getAddressById(1L);

        assertTrue(result.isPresent());
        assertEquals(address, result.get());
        verify(addressRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAddressById_NotFound() {
        when(addressRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Address> result = orderService.getAddressById(1L);

        assertFalse(result.isPresent());
        verify(addressRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAddressById_Exception() {
        when(addressRepository.findById(1L)).thenThrow(new RuntimeException());

        Optional<Address> result = orderService.getAddressById(1L);

        assertFalse(result.isPresent());
        verify(addressRepository, times(1)).findById(1L);
    }
}
