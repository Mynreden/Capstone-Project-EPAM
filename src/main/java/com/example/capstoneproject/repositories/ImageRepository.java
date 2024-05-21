package com.example.capstoneproject.repositories;

import com.example.capstoneproject.DTO.ImageDTO;
import com.example.capstoneproject.DTO.OrderItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<ImageDTO, Long> {
    List<ImageDTO> findAllByProductId(@Param("productId") Long productId);
}
