package com.example.capstoneproject.services.interfaces;

import com.example.capstoneproject.DTO.ProductDTO;
import com.example.capstoneproject.domain.Category;
import com.example.capstoneproject.domain.Product;
import com.example.capstoneproject.domain.ProductVariant;

import java.util.List;
import java.util.Optional;

public interface ProductServiceInterface {
    public List<Product> getAllProducts();
    public Optional<Product> getProductById(Long id);
    public Optional<Category> getCategoryById(Long id);
    public List<ProductVariant> getAllProductVariantsByProductId(Long id);
    public Optional<ProductVariant> getProductVariantById(Long id);


    }
