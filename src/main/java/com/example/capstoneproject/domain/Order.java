package com.example.capstoneproject.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class Order {
    private final Long id;
    private Long userId;
    private Long totalPrice;
    private List<Item> items;
    private Date date;
    private String status;
    private Address address;

    public Order(@JsonProperty("id") Long id,
                 @JsonProperty("userId") Long userId,
                 @JsonProperty("totalPrice") long totalPrice,
                 @JsonProperty("items") List<Item> items,
                 @JsonProperty("date") Date date,
                 @JsonProperty("status") String status,
                 @JsonProperty("address") Address address){
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.items = items;
        this.date = date;
        this.status = status;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
