package com.example.capstoneproject.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Category {
    private final Long id;
    private String name;
    private String description;

    @JsonCreator
    public Category(@JsonProperty("id") Long id,
                    @JsonProperty("name") String name,
                    @JsonProperty("description") String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Category(Category other) {
        this.id = other.id;
        this.name = other.name;
        this.description = other.description;
    }

    public Long getId() {
        return id;
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
}
