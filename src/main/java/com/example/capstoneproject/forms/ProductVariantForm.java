package com.example.capstoneproject.forms;

public class ProductVariantForm {
    private String color;
    private String size;
    private Long price;

    public ProductVariantForm(String color, String size, Long price) {
        this.color = color;
        this.size = size;
        this.price = price;
    }

    public ProductVariantForm() {
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
