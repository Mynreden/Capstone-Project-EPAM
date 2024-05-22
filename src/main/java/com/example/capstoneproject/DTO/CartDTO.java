package com.example.capstoneproject.DTO;

import org.jetbrains.annotations.Contract;

import javax.persistence.*;

@Entity
@Table(name = "CART")
public class CartDTO {
    @Id
    @Column(name = "ID")
    public Long id;

    @Column(name = "TOTAL_PRICE")
    public Long totalPrice;

    @Contract(pure = true)
    public CartDTO(Long id, long total_price){
        this.id = id;
        this.totalPrice = total_price;
    }

    public CartDTO(){

    }
}
