package com.example.capstoneproject.repositories;

import com.example.capstoneproject.DTO.OrderDTO;
import com.example.capstoneproject.DTO.OrderItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderDTO, Long> {

}
