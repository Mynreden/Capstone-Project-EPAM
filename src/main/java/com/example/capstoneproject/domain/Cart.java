package com.example.capstoneproject.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Cart {
    private final Long id;
    private Long totalPrice;
    private List<Item> items;

    public Cart(@JsonProperty("id") Long id,
                @JsonProperty("totalPrice") long totalPrice,
                @JsonProperty("items") List<Item> items){
        this.id = id;
        this.totalPrice = totalPrice;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
