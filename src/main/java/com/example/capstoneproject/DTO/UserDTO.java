package com.example.capstoneproject.DTO;

import org.jetbrains.annotations.Contract;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class UserDTO {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "FIRSTNAME")
    public String firstname;

    @Column(name = "LASTNAME")
    public String lastname;

    @Column(name = "EMAIL")
    public String email;

    @Column(name = "PASSWORD")
    public String password;

    @Column(name = "PHONE")
    public String phone;

    @Contract(pure = true)
    public UserDTO(Long id, String firstname, String lastname, String email, String password, String phone){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public UserDTO(){

    }
}