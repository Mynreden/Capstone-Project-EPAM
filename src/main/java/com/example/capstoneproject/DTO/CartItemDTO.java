package com.example.capstoneproject.DTO;


import org.jetbrains.annotations.Contract;

import javax.persistence.*;

@Entity
@Table(name = "CART_ITEM")
public class CartItemDTO {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "CART_ID")
    public Long cartId;

    @Column(name = "PRODUCT_VARIANT_ID")
    public Long productVariantId;

    @Column(name = "QUANTITY")
    public int quantity;

    @Contract(pure = true)
    public CartItemDTO(Long id, Long cartId, long productVariantId, int quantity){
        this.id = id;
        this.cartId = cartId;
        this.productVariantId = productVariantId;
        this.quantity = quantity;
    }

    public CartItemDTO(){

    }
}
