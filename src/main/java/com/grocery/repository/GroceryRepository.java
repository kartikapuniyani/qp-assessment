package com.grocery.repository;

import com.grocery.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryRepository extends JpaRepository<Item, Long> {

}
