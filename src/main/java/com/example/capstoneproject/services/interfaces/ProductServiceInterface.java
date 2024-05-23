package com.example.capstoneproject.services.interfaces;

import com.example.capstoneproject.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductServiceInterface {
    public List<Product> getAllProducts();
    public Optional<Product> getProductById(Long id);

    }
