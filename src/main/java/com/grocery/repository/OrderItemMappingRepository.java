package com.grocery.repository;

import com.grocery.entity.OrderItemMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemMappingRepository extends JpaRepository<OrderItemMapping, Long> {

}