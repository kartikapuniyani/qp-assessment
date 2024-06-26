package com.grocery.repository;

import com.grocery.entity.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDataRepository extends JpaRepository<OrderData, Long> {
    
}