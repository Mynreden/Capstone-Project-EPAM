package com.example.capstoneproject.DTO;

import org.jetbrains.annotations.Contract;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCTS")
public class ProductDTO {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "NAME")
    public String name;

    @Column(name = "DESCRIPTION")
    public String description;

    @Column(name = "CATEGORY_ID")
    public Long categoryId;

    @Contract(pure = true)
    public ProductDTO(Long id, String name, String description, Long categoryId){
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
    }

    public ProductDTO(){

    }
}