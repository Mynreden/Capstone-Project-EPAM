package com.example.capstoneproject.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "IMAGES")
public class Image {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "URL")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @JsonCreator
    public Image(@JsonProperty("id") Long id,
                 @JsonProperty("url") String url,
                 @JsonProperty("product") Product product) {
        this.id = id;
        this.url = url;
        this.product = product;
    }

    public Image() {

    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setURL(String url) {
        this.url = url;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
