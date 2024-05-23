package com.example.capstoneproject.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> images;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductVariant> productVariants;

    private Long minPrice = 0L;

    @JsonCreator
    public Product(@JsonProperty("id") Long id,
                        @JsonProperty("name") String name,
                        @JsonProperty("description") String description,
                        @JsonProperty("category") Category category,
                        @JsonProperty("images") List<Image> images,
                   @JsonProperty("productVariants") List<ProductVariant> productVariants) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.images = images;
        this.productVariants = productVariants;
    }

    public Product() {

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

    public void setImages(List<Image> images) {
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

    public List<Image> getImages() {
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
        if (getProductVariants() != null){
            Long minPrice = getProductVariants()
                    .stream()
                    .mapToLong(ProductVariant::getPrice)
                    .min()
                    .orElse(0L);
            setMinPrice(minPrice);
        }
    }

    public void setId(Long id) {
        this.id = id;
    }
}
