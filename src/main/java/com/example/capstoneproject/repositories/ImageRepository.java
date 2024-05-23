package com.example.capstoneproject.repositories;

import com.example.capstoneproject.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByProductId(@Param("productId") Long productId);
}
