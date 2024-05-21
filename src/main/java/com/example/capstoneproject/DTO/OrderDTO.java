package com.example.capstoneproject.DTO;

import org.jetbrains.annotations.Contract;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ORDERS")
public class OrderDTO {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "USER_ID")
    public Long userId;

    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    public Date date;

    @Column(name = "STATUS")
    public String status;

    @Column(name = "TOTAL_PRICE")
    public int totalPrice;

    @Column(name = "ADDRESS_ID")
    public Long addressId;

    @Contract(pure = true)
    public OrderDTO(Long id, Long userId, Date date, String status, int totalPrice, Long addressId){
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.totalPrice = totalPrice;
        this.status = status;
        this.addressId = addressId;
    }

    public OrderDTO(){

    }
}