package com.example.capstoneproject.services;

import com.example.capstoneproject.domain.Category;
import com.example.capstoneproject.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> getCategoryByName(String name){
        try{
            return categoryRepository.findByName(name);
        } catch (Throwable error){
            return Optional.empty();
        }
    }

    public List<Category> getAllCategories(){
        try{
            return categoryRepository.findAll();
        } catch (Throwable error){
            return List.of();
        }
    }

    public Category save(Category category){
        try{
            return categoryRepository.save(category);
        } catch (Throwable error){
            return null;
        }
    }
}
