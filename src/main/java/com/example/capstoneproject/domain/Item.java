package com.example.capstoneproject.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
    private final Long id;
    private ProductVariant productVariant;
    private int quantity;

    public Item(@JsonProperty("id")  Long id,
                     @JsonProperty("productVariant") ProductVariant productVariant,
                     @JsonProperty("quantity") int quantity){
        this.id = id;
        this.productVariant = productVariant;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public ProductVariant getProductVariant() {
        return productVariant;
    }

    public void setProductVariant(ProductVariant productVariant) {
        this.productVariant = productVariant;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
