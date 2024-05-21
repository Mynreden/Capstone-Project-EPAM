package com.example.capstoneproject.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Product {
    private final Long id;
    private String name;
    private String description;
    private Category category;
    private List<String> images;
    private List<ProductVariant> productVariants;

    private Long minPrice = 0L;

    @JsonCreator
    public Product(@JsonProperty("id") Long id,
                        @JsonProperty("name") String name,
                        @JsonProperty("description") String description,
                        @JsonProperty("category") Category category,
                        @JsonProperty("images") List<String> images,
                   @JsonProperty("productVariants") List<ProductVariant> productVariants) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.images = images;
        this.productVariants = productVariants;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public List<String> getImages() {
        return images;
    }

    public Long getId() {
        return id;
    }

    public List<ProductVariant> getProductVariants() {
        return productVariants;
    }

    public void setProductVariants(List<ProductVariant> productVariants) {
        this.productVariants = productVariants;
    }

    public Long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Long minPrice) {
        this.minPrice = minPrice;
    }

    public void calculateMinPrice(){
        Long minPrice = getProductVariants()
                .stream()
                .mapToLong(ProductVariant::getPrice)
                .min()
                .orElse(0L);
        setMinPrice(minPrice);
    }
}
