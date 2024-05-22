package com.example.capstoneproject.services;

import com.example.capstoneproject.DTO.*;
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

    public Optional<Cart> getCartById(Long id) {
        Optional<Cart> optionalCart = findCartById(id);
        if (optionalCart.isPresent()){
            return optionalCart;
        }
        cartRepository.save(new CartDTO(id, 0));
        return findCartById(id);
    }

    public Optional<Cart> addCartItems(Cart cart, Long productVariantId, int quantity){
        Optional<CartItemDTO> optionalCartItemDTO = cartItemRepository.findByCartIdAndProductVariantId(cart.getId(), productVariantId);
        if (optionalCartItemDTO.isPresent()){

        }
        CartItemDTO savedCartItemDTO = cartItemRepository.save(new CartItemDTO(null, cart.getId(),
                productVariantId, quantity));
        Item item = itemDTOToItem(savedCartItemDTO);
        if (item == null){
            return Optional.empty();
        }
        cartRepository.save(new CartDTO(cart.getId(), cart.getTotalPrice() + item.getQuantity() * item.getProductVariant().getPrice()));
        return findCartById(cart.getId());
    }

    private Optional<Cart> findCartById(Long id){
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
        Optional<ProductDTO> optionalProductDTO = productService.getProductDTOByProductVariantId(productVariant.getId());

        if (optionalProductDTO.isEmpty()){
            return null;
        }
        ProductDTO productDTO = optionalProductDTO.get();
        List<String> images = productService.getProductImagesByProductId(productDTO.id);
        if (images.isEmpty()){
            images.add("/img/default.png");
        }
        return new Item(cartItemDTO.id, productVariant, cartItemDTO.quantity, productDTO.id, productDTO.name, images.get(0));
    }

    public Optional<Cart> changeProductAmountInCart(Cart cart, Long productVariantId, int action){
        Optional<CartItemDTO> optionalCartItemDTO = cartItemRepository.findByCartIdAndProductVariantId(cart.getId(), productVariantId);
        if (optionalCartItemDTO.isEmpty()){
            return Optional.empty();
        }
        CartItemDTO cartItemDTO = optionalCartItemDTO.get();

        if (cartItemDTO.quantity + action == 0){
            cartItemRepository.delete(cartItemDTO);
        } else {
            cartItemDTO.quantity = cartItemDTO.quantity + action;
            cartItemRepository.save(cartItemDTO);

        }
        Optional<ProductVariant> optionalProductVariant = productService.getProductVariantById(productVariantId);
        if (optionalProductVariant.isEmpty()){
            return Optional.empty();
        }

        cartRepository.save(new CartDTO(cart.getId(), cart.getTotalPrice() + optionalProductVariant.get().getPrice() * action));

        return getCartById(cart.getId());
    }

}
