package com.example.capstoneproject.repositories;

import com.example.capstoneproject.DTO.AddressDTO;
import com.example.capstoneproject.DTO.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressDTO, Long> {

}
