package com.example.capstoneproject.forms;

import java.util.List;

public class ProductForm {
    private String name;
    private String description;
    private String categoryName;
    private String newCategoryName;
    private String categoryDescription;
    private List<ProductVariantForm> variants;

    public ProductForm(String name,
                       String description,
                       String categoryName,
                       String newCategoryName,
                       String categoryDescription,
                       List<ProductVariantForm> variants){
        this.name = name;
        this.description = description;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.variants = variants;
        this.newCategoryName = newCategoryName;
    }

    public ProductForm(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public List<ProductVariantForm> getVariants() {
        return variants;
    }

    public void setVariants(List<ProductVariantForm> variants) {
        this.variants = variants;
    }

    public String getNewCategoryName() {
        return newCategoryName;
    }

    public void setNewCategoryName(String newCategoryName) {
        this.newCategoryName = newCategoryName;
    }

    // Constructor, getters, and setters
}
