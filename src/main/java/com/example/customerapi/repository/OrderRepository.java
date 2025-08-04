package com.example.customerapi.repository;

import com.example.customerapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    // Count how many orders belong to a specific user
    Long countByUserId(Integer userId);
}