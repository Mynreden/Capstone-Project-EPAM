package com.example.capstoneproject.repositories;

import com.example.capstoneproject.DTO.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductDTO, Long> {

}
