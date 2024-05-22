package com.example.capstoneproject.repositories;

import com.example.capstoneproject.DTO.CartDTO;
import com.example.capstoneproject.DTO.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartDTO, Long> {
}
