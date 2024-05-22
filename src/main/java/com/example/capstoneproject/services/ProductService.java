package com.example.capstoneproject.services;

import com.example.capstoneproject.DTO.CategoryDTO;
import com.example.capstoneproject.DTO.ImageDTO;
import com.example.capstoneproject.DTO.ProductDTO;
import com.example.capstoneproject.DTO.ProductVariantDTO;
import com.example.capstoneproject.domain.Category;
import com.example.capstoneproject.domain.Product;
import com.example.capstoneproject.domain.ProductVariant;
import com.example.capstoneproject.repositories.*;
import com.example.capstoneproject.services.interfaces.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductServiceInterface {
    private final ProductRepository productRepository;
    private final ProductVariantRepository productVariantRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          ProductVariantRepository productVariantRepository,
                          CategoryRepository categoryRepository,
                          ImageRepository imageRepository) {
        this.productVariantRepository = productVariantRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.imageRepository = imageRepository;
    }

    public List<Product> getAllProducts(){
        List<ProductDTO> productDTOList = productRepository.findAll();
        HashMap<Long, Category> categories = new HashMap<>();
        List<Product> result = new ArrayList<>();
        for (ProductDTO productDTO : productDTOList) {
            Category category;
            if (categories.containsKey(productDTO.categoryId)){
                category = new Category(categories.get(productDTO.categoryId));
            } else {
                Optional<Category> optionalCategory = getCategoryById(productDTO.categoryId);
                if (optionalCategory.isEmpty()) {
                    return List.of();
                }
                category = optionalCategory.get();
                categories.put(productDTO.categoryId, category);
            }
            List<String> images = extractURLFromImageDTO(imageRepository.findAllByProductId(productDTO.id));
            List<ProductVariant> productVariants = getAllProductVariantsByProductId(productDTO.id);

            Product product = new Product(productDTO.id, productDTO.name, productDTO.description, category, images, productVariants);
            product.calculateMinPrice();
            result.add(product);
        }
        return result;
    }

    public Optional<Product> getProductById(Long id){
        Optional<ProductDTO> optionalProductDTO = productRepository.findById(id);
        if (optionalProductDTO.isEmpty()){
            return Optional.empty();
        }
        ProductDTO productDTO = optionalProductDTO.get();

        Optional<Category> optionalCategory = getCategoryById(productDTO.categoryId);
        if (optionalCategory.isEmpty()){
            return Optional.empty();
        }
        Category category = optionalCategory.get();

        List<String> images = extractURLFromImageDTO(imageRepository.findAllByProductId(id));

        List<ProductVariant> productVariants = getAllProductVariantsByProductId(id);

        Product product = new Product(id,productDTO.name, productDTO.description, category, images, productVariants);
        product.calculateMinPrice();

        return Optional.of(product);
    }

    public Optional<Category> getCategoryById(Long id){
        Optional<CategoryDTO> optionalCategoryDTO = categoryRepository.findById(id);
        if (optionalCategoryDTO.isEmpty()){
            return Optional.empty();
        }
        CategoryDTO categoryDTO = optionalCategoryDTO.get();

        return Optional.of(new Category(categoryDTO.id, categoryDTO.name, categoryDTO.description));
    }

    public List<ProductVariant> getAllProductVariantsByProductId(Long id){
        List<ProductVariantDTO> productVariantDTOList = productVariantRepository.findAllByProductId(id);
        List<ProductVariant> result = new ArrayList<>();

        for (ProductVariantDTO productVariantDTO : productVariantDTOList){
            ProductVariant productVariant = new ProductVariant(productVariantDTO.id, productVariantDTO.color, productVariantDTO.size, productVariantDTO.price);
            result.add(productVariant);
        }
        return result;
    }

    public Optional<ProductVariant> getProductVariantById(Long id){
        Optional<ProductVariantDTO> optionalProductVariantDTO = productVariantRepository.findById(id);
        if (optionalProductVariantDTO.isEmpty()){
            return Optional.empty();
        }
        ProductVariantDTO productVariantDTO = optionalProductVariantDTO.get();
        return Optional.of(new ProductVariant(productVariantDTO.id, productVariantDTO.color, productVariantDTO.size, productVariantDTO.price));
    }

    private List<String> extractURLFromImageDTO(List<ImageDTO> imageDTOs) {
        List<String> urls = new ArrayList<>();
        for (ImageDTO imageDTO : imageDTOs) {
            urls.add(imageDTO.url);
        }
        return urls;
    }

    public Long getProductIdByProductVariantId(Long id){
        Optional<ProductVariantDTO> optionalProductVariantDTO = productVariantRepository.findById(id);
        if (optionalProductVariantDTO.isEmpty()){
            return null;
        }
        return optionalProductVariantDTO.get().productId;
    }

    Optional<ProductDTO> getProductDTOByProductVariantId(Long id){
        Optional<ProductVariantDTO> optionalProductVariantDTO = productVariantRepository.findById(id);
        if (optionalProductVariantDTO.isEmpty()){
            return Optional.empty();
        }
        return productRepository.findById(optionalProductVariantDTO.get().productId);
    }

    List<String> getProductImagesByProductId(Long id){
        return extractURLFromImageDTO(imageRepository.findAllByProductId(id));
    }

}
