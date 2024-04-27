package com.grocery.service;

import com.grocery.co.GroceryCO;
import com.grocery.co.GroceryUpdateCO;
import com.grocery.co.OrderCO;
import com.grocery.enums.RoleType;
import com.grocery.vo.GroceryVO;

import java.util.List;

public interface GroceryService {

    Long save(GroceryCO groceryCO);

    List<GroceryVO> getAll(RoleType roleType);

    void delete(Long id, RoleType roleType);

    void update(Long id, GroceryUpdateCO groceryUpdateCO);

    List<GroceryVO> getAllAvailableGrocery(RoleType roleType);

    Long orderGrocery(RoleType roleType, OrderCO orderCO);
}