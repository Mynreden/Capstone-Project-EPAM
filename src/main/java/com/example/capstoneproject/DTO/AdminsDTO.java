package com.example.capstoneproject.DTO;

import org.jetbrains.annotations.Contract;

import javax.persistence.*;

@Entity
@Table(name = "ADMINS")
public class AdminsDTO {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long userId;

    @Contract(pure = true)
    public AdminsDTO(Long userId){
        this.userId = userId;
    }

    public AdminsDTO(){

    }
}
