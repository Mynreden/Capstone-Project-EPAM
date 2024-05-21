package com.example.capstoneproject.DTO;

import org.jetbrains.annotations.Contract;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCTVARIANTS")
public class ProductVariantDTO {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "PRODUCT_ID")
    public Long productId;

    @Column(name = "SIZE")
    public String size;

    @Column(name = "COLOR")
    public String color;

    @Column(name = "PRICE")
    public Long price;

    @Contract(pure = true)
    public ProductVariantDTO(Long id, Long productId, String size, String color, Long price){
        this.id = id;
        this.productId = productId;
        this.size = size;
        this.price = price;
        this.color = color;
    }

    public ProductVariantDTO(){

    }
}