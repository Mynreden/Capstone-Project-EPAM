package com.example.capstoneproject.DTO;

import org.jetbrains.annotations.Contract;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESSES")
public class AddressDTO {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "CITY")
    public String city;

    @Column(name = "STREET")
    public String street;

    @Column(name = "HOUSE")
    public String house;

    @Column(name = "APARTMENT")
    public String apartment;

    @Contract(pure = true)
    public AddressDTO(Long id, String city, String street, String house, String apartment){
        this.id = id;
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
    }

    public AddressDTO(){

    }
}
