package com.example.capstoneproject.services;

import com.example.capstoneproject.domain.*;
import com.example.capstoneproject.repositories.AddressRepository;
import com.example.capstoneproject.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final AddressRepository addressRepository;
    private final UserService userService;


    @Autowired
    public OrderService(OrderRepository orderRepository,
                        UserService userService,
                        AddressRepository addressRepository,
                        CartService cartService) {
        this.orderRepository = orderRepository;
        this.addressRepository = addressRepository;
        this.cartService = cartService;
        this.userService = userService;
    }

    public List<Order> findOrdersByUserId(Long id){
        try{
            return orderRepository.findAllByUserId(id);
        } catch (Throwable error){
            return List.of();
        }
    }

    public Optional<Order> createOrder(Long userId, Long addressId){
        try{
            Optional<Cart> optionalCart = cartService.getCartById(userId);
            if (optionalCart.isEmpty()){
                return Optional.empty();
            }
            Cart cart = optionalCart.get();

            Optional<User> optionalUser = userService.findById(userId);
            if (optionalUser.isEmpty()){
                return Optional.empty();
            }
            User user = optionalUser.get();


            Optional<Address> optionalAddress = addressRepository.findById(addressId);
            if (optionalAddress.isEmpty()){
                return Optional.empty();
            }
            Address address = optionalAddress.get();
            Order order = orderRepository.save(new Order(null, user, cart.getTotalPrice(), new ArrayList<>(), new Date(), OrderStatus.Pending,address));

            List<OrderItem> orderItems = new ArrayList<>();
            for (CartItem cartItem : cart.getCartItems()){
                orderItems.add(new OrderItem(null, cartItem.getProductVariant(), cartItem.getQuantity(), order));
                System.out.println(cartItem.getProductVariant().getPrice());
            }
            order.setOrderItems(orderItems);

            order = orderRepository.save(order);

            return Optional.of(order);
        } catch (Error error){
            System.out.println(error.getMessage());
            return Optional.empty();
        }
    }

    public Optional<Address> createAddress(String city, String street, String house, String apartment){
        try {
            Address address = addressRepository.save(new Address(null, city, street, house, apartment));
            return Optional.of(address);
        } catch (Throwable error){
            return Optional.empty();
        }
    }

    public Optional<Address> getAddressById(Long id) {
        try{
            return addressRepository.findById(id);
        } catch (Throwable error){
            return Optional.empty();
        }
    }

    public void updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + orderId));
        order.setStatus(OrderStatus.valueOf(status));
        System.out.println(order.getStatus().name());
        orderRepository.save(order);
    }

    public Page<Order> getOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderRepository.findAll(pageable);
    }
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}
