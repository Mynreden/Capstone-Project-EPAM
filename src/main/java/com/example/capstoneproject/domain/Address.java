package com.example.capstoneproject.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {
    private final Long id;
    private String city;
    private String street;

    private String house;

    private String apartment;

    public Address(@JsonProperty("id") Long id,
                   @JsonProperty("city") String city,
                   @JsonProperty("street") String street,
                   @JsonProperty("house") String house,
                   @JsonProperty("apartment") String apartment){
        this.id = id;
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
    }


    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }
}
