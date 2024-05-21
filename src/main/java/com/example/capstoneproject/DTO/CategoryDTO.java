package com.example.capstoneproject.DTO;

import org.jetbrains.annotations.Contract;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORY")
public class CategoryDTO {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "NAME")
    public String name;

    @Column(name = "DESCRIPTION")
    public String description;

    @Contract(pure = true)
    public CategoryDTO(Long id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public CategoryDTO(){

    }
}
