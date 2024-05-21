package com.example.capstoneproject.DTO;

import org.jetbrains.annotations.Contract;

import javax.persistence.*;

@Entity
@Table(name = "IMAGES")
public class ImageDTO {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "PRODUCT_ID")
    public Long productId;

    @Column(name = "URL")
    public String url;

    @Contract(pure = true)
    public ImageDTO(Long id, Long productId, String url){
        this.id = id;
        this.productId = productId;
        this.url = url;
    }

    public ImageDTO(){

    }
}
