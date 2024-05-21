package com.example.capstoneproject.repositories;

import com.example.capstoneproject.DTO.ProductVariantDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariantDTO, Long> {
    List<ProductVariantDTO> findAllByProductId(@Param("productId") Long productId);

}
