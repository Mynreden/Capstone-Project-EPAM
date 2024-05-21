package com.example.capstoneproject.repositories;

import com.example.capstoneproject.DTO.OrderItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemDTO, Long> {
    List<OrderItemDTO> findAllByOrderID(@Param("orderID") Long orderID);
}
