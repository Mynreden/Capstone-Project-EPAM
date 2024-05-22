package com.example.capstoneproject.repositories;

import com.example.capstoneproject.DTO.CartItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemDTO, Long> {
    List<CartItemDTO> findAllByCartId(@Param("cartID") Long cartID);
    Optional<CartItemDTO> findByCartIdAndProductVariantId(@Param("cartID") Long cartID, @Param("productVariantID") Long productVariantID);
}
