package com.example.capstoneproject.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "Admins")
public class Admin {
    @Id
    @Column(name = "User_ID")
    private Long user_id;


    public Admin(@JsonProperty("user_id") Long user_id){
        this.user_id = user_id;
    }

    public Admin(){

    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}