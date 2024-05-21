package com.example.capstoneproject.services;

import com.example.capstoneproject.DTO.CartDTO;
import com.example.capstoneproject.DTO.CartItemDTO;
import com.example.capstoneproject.DTO.OrderDTO;
import com.example.capstoneproject.DTO.OrderItemDTO;
import com.example.capstoneproject.controllers.frontend.CartController;
import com.example.capstoneproject.domain.*;
import com.example.capstoneproject.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductService productService;

    @Autowired
    public CartService(CartRepository cartRepository,
                       CartItemRepository cartItemRepository,
                        ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
    }

    public Optional<Cart> createCartByUserId(Long id) {
        Optional<Cart> optionalCart = findCartByUserId(id);
        if (optionalCart.isPresent()){
            return optionalCart;
        }
        cartRepository.save(new CartDTO(id, 0));
        return findCartByUserId(id);
    }

    public Optional<Cart> addCartItems(CartItemDTO cartItemDTO, Long cartId){
        CartItemDTO savedCartItemDTO = cartItemRepository.save(cartItemDTO);
        Item item = itemDTOToItem(savedCartItemDTO);
        if (item == null){
            return Optional.empty();
        }
        return findCartById(cartId);
    }

    public Optional<Cart> findCartByUserId(Long id){
        return findCartById(id);
    }

    public Optional<Cart> findCartById(Long id){
        Optional<CartDTO> optionalCartDTO = cartRepository.findById(id);
        if (optionalCartDTO.isEmpty()){
            return Optional.empty();
        }
        CartDTO cartDTO = optionalCartDTO.get();

        List<CartItemDTO> cartItemDTOList = cartItemRepository.findAllByCartId(id);
        List<Item> items = itemDTOListToItemList(cartItemDTOList);

        return Optional.of(new Cart(id, cartDTO.totalPrice, items));
    }

    private List<Item> itemDTOListToItemList(List<CartItemDTO> orderItemDTOList){
        List<Item> list = new ArrayList<>();
        for (CartItemDTO orderItemDTO : orderItemDTOList){
            Item item = itemDTOToItem(orderItemDTO);
            list.add(item);
        }
        return list;
    }
    private Item itemDTOToItem(CartItemDTO cartItemDTO){
        Optional<ProductVariant> optionalProductVariant = productService.getProductVariantById(cartItemDTO.productVariantId);
        if (optionalProductVariant.isEmpty()){
            return null;
        }
        ProductVariant productVariant = optionalProductVariant.get();
        return new Item(cartItemDTO.id, productVariant, cartItemDTO.quantity);
    }

}
