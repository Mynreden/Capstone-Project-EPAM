package com.example.capstoneproject.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "TOTAL_PRICE")
    private Long totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;
    @Column(name = "DATE")
    private Date date;
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private OrderStatus status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    public Order(@JsonProperty("id") Long id,
                 @JsonProperty("userId") User user,
                 @JsonProperty("totalPrice") long totalPrice,
                 @JsonProperty("items") List<OrderItem> orderItems,
                 @JsonProperty("date") Date date,
                 @JsonProperty("status") OrderStatus status,
                 @JsonProperty("address") Address address){
        this.id = id;
        this.user = user;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
        this.date = date;
        this.status = status;
        this.address = address;
    }

    public Order() {

    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
