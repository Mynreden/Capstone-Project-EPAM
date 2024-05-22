package com.example.capstoneproject.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
    private final Long id;
    private ProductVariant productVariant;
    private int quantity;
    private final Long productId;
    private String productName;
    private String productImg;
    public Item(@JsonProperty("id")  Long id,
                @JsonProperty("productVariant") ProductVariant productVariant,
                @JsonProperty("quantity") int quantity,
                @JsonProperty("productId") Long productId,
                @JsonProperty("productName") String productName,
                @JsonProperty("productImg") String productImg){
        this.id = id;
        this.productVariant = productVariant;
        this.quantity = quantity;
        this.productId = productId;
        this.productName = productName;
        this.productImg = productImg;
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

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }
}
