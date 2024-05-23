package com.example.capstoneproject.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCTVARIANTS")
public class ProductVariant {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "COLOR")

    private String color;
    @Column(name = "SIZE")

    private String size;
    @Column(name = "PRICE")
    private Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @JsonCreator
    public ProductVariant(@JsonProperty("id") Long id,
                          @JsonProperty("color") String color,
                          @JsonProperty("size") String size,
                          @JsonProperty("price") Long price,
                          @JsonProperty("product") Product product) {
        this.id = id;
        this.color = color;
        this.size = size;
        this.price = price;
        this.product = product;
    }

    public ProductVariant() {

    }

    public Long getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
