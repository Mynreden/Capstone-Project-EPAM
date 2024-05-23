package com.example.capstoneproject.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "CART")
public class Cart {
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "TOTAL_PRICE")
    private Long totalPrice;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> cartItems;

    public Cart(@JsonProperty("id") Long id,
                @JsonProperty("totalPrice") long totalPrice,
                @JsonProperty("cartItems") List<CartItem> cartItems){
        this.id = id;
        this.totalPrice = totalPrice;
        this.cartItems = cartItems;
    }

    public Cart() {

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

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> items) {
        this.cartItems = items;
    }
}
