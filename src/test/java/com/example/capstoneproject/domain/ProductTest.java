package com.example.capstoneproject.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    public void testCalculateMinPrice_NoVariants() {
        Product product = new Product();
        product.setProductVariants(List.of());

        product.calculateMinPrice();

        assertEquals(0L, product.getMinPrice());
    }

    @Test
    public void testCalculateMinPrice_WithVariants() {
        ProductVariant variant1 = new ProductVariant();
        variant1.setPrice(100L);

        ProductVariant variant2 = new ProductVariant();
        variant2.setPrice(200L);

        ProductVariant variant3 = new ProductVariant();
        variant3.setPrice(50L);

        Product product = new Product();
        product.setProductVariants(List.of(variant1, variant2, variant3));

        product.calculateMinPrice();

        assertEquals(50L, product.getMinPrice());
    }

    @Test
    public void testCalculateMinPrice_WithNullVariants() {
        Product product = new Product();
        product.setProductVariants(null);

        product.calculateMinPrice();

        assertEquals(0L, product.getMinPrice());
    }

    @Test
    public void testGettersAndSetters() {
        Category category = new Category();
        Image image = new Image();
        ProductVariant productVariant = new ProductVariant();

        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setCategory(category);
        product.setImages(List.of(image));
        product.setProductVariants(List.of(productVariant));
        product.setMinPrice(99L);

        assertEquals(1L, product.getId());
        assertEquals("Test Product", product.getName());
        assertEquals("Test Description", product.getDescription());
        assertEquals(category, product.getCategory());
        assertEquals(List.of(image), product.getImages());
        assertEquals(List.of(productVariant), product.getProductVariants());
        assertEquals(99L, product.getMinPrice());
    }
}
