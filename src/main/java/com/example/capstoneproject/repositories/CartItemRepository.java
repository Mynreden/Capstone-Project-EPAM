package com.example.capstoneproject.repositories;

import com.example.capstoneproject.DTO.CartItemDTO;
import com.example.capstoneproject.DTO.CategoryDTO;
import com.example.capstoneproject.DTO.OrderItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemDTO, Long> {
    List<CartItemDTO> findAllByCartId(@Param("cartID") Long cartID);
}
