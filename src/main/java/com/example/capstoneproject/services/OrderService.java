package com.example.capstoneproject.services;

import com.example.capstoneproject.DTO.AddressDTO;
import com.example.capstoneproject.DTO.OrderDTO;
import com.example.capstoneproject.DTO.OrderDTO;
import com.example.capstoneproject.DTO.OrderItemDTO;
import com.example.capstoneproject.domain.*;
import com.example.capstoneproject.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;
    private final AddressRepository addressRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository,
                        ProductService productService,
                        AddressRepository addressRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productService = productService;
        this.addressRepository = addressRepository;
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
            Item item = new Item(orderItemDTO.id, productVariant, orderItemDTO.amount);
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
