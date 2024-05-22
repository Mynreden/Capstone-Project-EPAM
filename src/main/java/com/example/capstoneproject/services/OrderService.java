package com.example.capstoneproject.services;

import com.example.capstoneproject.DTO.*;
import com.example.capstoneproject.DTO.OrderDTO;
import com.example.capstoneproject.domain.*;
import com.example.capstoneproject.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;
    private final CartService cartService;
    private final AddressRepository addressRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository,
                        ProductService productService,
                        AddressRepository addressRepository,
                        CartService cartService) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productService = productService;
        this.addressRepository = addressRepository;
        this.cartService = cartService;
    }

    public List<Order> findOrdersByUserId(Long id){
        List<OrderDTO> orderDTOList = orderRepository.findAllByUserId(id);
        List<Order> orders = new ArrayList<>();
        for (OrderDTO orderDTO : orderDTOList){
            List<OrderItemDTO> orderItemDTOList = orderItemRepository.findAllByOrderID(orderDTO.id);
            List<Item> items = itemDTOToItem(orderItemDTOList);
            Optional<Address> optionalAddress = getAddressById(orderDTO.addressId);
            if (optionalAddress.isEmpty()){
                continue;
            }
            Address address = optionalAddress.get();

            orders.add(new Order(id, orderDTO.userId, orderDTO.totalPrice, items, orderDTO.date, orderDTO.status, address));
        }
        return orders;
    }

    public Optional<Order> createOrder(Long userId, Long addressId){
        Optional<Cart> optionalCart = cartService.getCartById(userId);
        if (optionalCart.isEmpty()){
            return Optional.empty();
        }
        Cart cart = optionalCart.get();
        OrderDTO orderDTO = orderRepository.save(new OrderDTO(null, userId, new Date(), "In progress", cart.getTotalPrice().intValue(), addressId));
        for (Item item : cart.getItems()){
            orderItemRepository.save(new OrderItemDTO(null, orderDTO.id, item.getProductVariant().getId(), item.getQuantity()));
        }
        Optional<AddressDTO> optionalAddressDTO = addressRepository.findById(addressId);
        if (optionalAddressDTO.isEmpty()){
            return Optional.empty();
        }
        AddressDTO addressDTO = optionalAddressDTO.get();
        Address address = new Address(addressDTO.id, addressDTO.city, addressDTO.street, addressDTO.house, addressDTO.apartment);
        return Optional.of(new Order(orderDTO.id, orderDTO.userId, cart.getTotalPrice(), cart.getItems(), orderDTO.date, orderDTO.status, address));
    }

    public Optional<Address> createAddress(String city, String street, String house, String apartment){
        AddressDTO addressDTO = addressRepository.save(new AddressDTO(null, city, street, house, apartment));
        return Optional.of(new Address(addressDTO.id, city, street, house, apartment));
    }

    public Optional<Order> findOrderById(Long id){
        Optional<OrderDTO> optionalOrderDTO = orderRepository.findById(id);
        if (optionalOrderDTO.isEmpty()){
            return Optional.empty();
        }
        OrderDTO orderDTO = optionalOrderDTO.get();

        List<OrderItemDTO> orderItemDTOList = orderItemRepository.findAllByOrderID(id);
        List<Item> items = itemDTOToItem(orderItemDTOList);
        Optional<Address> optionalAddress = getAddressById(orderDTO.addressId);
        if (optionalAddress.isEmpty()){
            return Optional.empty();
        }
        Address address = optionalAddress.get();
        return Optional.of(new Order(id, orderDTO.userId, orderDTO.totalPrice, items, orderDTO.date, orderDTO.status, address));
    }

    private List<Item> itemDTOToItem(List<OrderItemDTO>orderItemDTOList){
        List<Item> list = new ArrayList<>();
        for (OrderItemDTO orderItemDTO : orderItemDTOList){
            Optional<ProductVariant> optionalProductVariant = productService.getProductVariantById(orderItemDTO.productVariantId);
            if (optionalProductVariant.isEmpty()){
                continue;
            }
            ProductVariant productVariant = optionalProductVariant.get();
            Optional<ProductDTO> optionalProductDTO = productService.getProductDTOByProductVariantId(productVariant.getId());

            if (optionalProductDTO.isEmpty()){
                continue;
            }
            ProductDTO productDTO = optionalProductDTO.get();
            List<String> images = productService.getProductImagesByProductId(productDTO.id);
            if (images.isEmpty()){
                images.add("/img/default.png");
            }
            Item item = new Item(orderItemDTO.id, productVariant, orderItemDTO.amount, productDTO.id, productDTO.name, images.get(0));
            list.add(item);
        }
        return list;
    }

    public Optional<Address> getAddressById(Long id) {
        Optional<AddressDTO> optionalAddressDTO = addressRepository.findById(id);
        if (optionalAddressDTO.isEmpty()) {
            return Optional.empty();
        }
        AddressDTO addressDTO = optionalAddressDTO.get();
        return Optional.of(new Address(addressDTO.id, addressDTO.city, addressDTO.street, addressDTO.house, addressDTO.apartment));

    }




}
