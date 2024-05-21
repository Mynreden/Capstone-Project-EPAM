package com.example.capstoneproject.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductVariant {
    private final Long id;
    private String color;
    private String size;
    private Long price;

    @JsonCreator
    public ProductVariant(@JsonProperty("id") Long id,
                          @JsonProperty("color") String color,
                          @JsonProperty("size") String size,
                          @JsonProperty("price") Long price) {
        this.id = id;
        this.color = color;
        this.size = size;
        this.price = price;
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
}
