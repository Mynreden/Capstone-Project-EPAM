package com.example.capstoneproject.repositories;

import com.example.capstoneproject.domain.Order;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(@Param("userId") Long userId);
    @NotNull Page<Order> findAll(@Param("pageable") @NotNull Pageable pageable);

}
