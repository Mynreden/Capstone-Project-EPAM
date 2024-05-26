package com.example.capstoneproject.services;

import com.example.capstoneproject.domain.Product;
import com.example.capstoneproject.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        try {
            List<Product> products = productRepository.findAll();
            for (Product product : products){
                product.calculateMinPrice();
            }
            return products;
        } catch (Throwable error){
            return List.of();
        }
    }

    public Optional<Product> getProductById(Long id){
        try {
            Optional<Product> optionalProduct = productRepository.findById(id);
            if (optionalProduct.isEmpty()){
                return Optional.empty();
            }
            Product product = optionalProduct.get();
            product.calculateMinPrice();
            return Optional.of(product);
        } catch (Throwable error){
            return Optional.empty();
        }
    }

    public void saveProduct(Product product){
        try {
            productRepository.save(product);
        } catch (Throwable error){
            System.out.println(error.getMessage());
        }
    }

}
