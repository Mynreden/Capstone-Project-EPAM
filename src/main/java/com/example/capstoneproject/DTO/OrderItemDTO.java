package com.example.capstoneproject.DTO;


import org.jetbrains.annotations.Contract;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_ITEMS")
public class OrderItemDTO {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "ORDER_ID")
    public Long orderID;

    @Column(name = "PRODUCT_VARIANT_ID")
    public Long productVariantId;

    @Column(name = "AMOUNT")
    public int amount;

    @Contract(pure = true)
    public OrderItemDTO(Long id, Long orderID, Long productVariantId, int amount){
        this.id = id;
        this.orderID = orderID;
        this.productVariantId = productVariantId;
        this.amount = amount;
    }

    public OrderItemDTO(){

    }
}
