package com.grocery.service.impl;

import com.grocery.co.GroceryCO;
import com.grocery.co.GroceryUpdateCO;
import com.grocery.co.OrderCO;
import com.grocery.entity.Item;
import com.grocery.entity.OrderData;
import com.grocery.entity.OrderItemMapping;
import com.grocery.entity.UserData;
import com.grocery.enums.RoleType;
import com.grocery.repository.ItemRepository;
import com.grocery.repository.OrderDataRepository;
import com.grocery.repository.OrderItemMappingRepository;
import com.grocery.repository.UserDataRepository;
import com.grocery.service.GroceryService;
import com.grocery.vo.GroceryVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GroceryServiceImpl implements GroceryService {

    private ItemRepository itemRepository;

    private final OrderDataRepository orderRepository;

    private final UserDataRepository userRepository;

    private final OrderItemMappingRepository orderItemMappingRepository;

    public GroceryServiceImpl(ItemRepository groceryRepository, OrderDataRepository orderGroceryRepository, UserDataRepository userRepository, OrderItemMappingRepository orderItemMappingRepository) {
        this.itemRepository = groceryRepository;
        this.orderRepository = orderGroceryRepository;
        this.userRepository = userRepository;
        this.orderItemMappingRepository = orderItemMappingRepository;
    }

    @Override
    public Long save(RoleType roleType, GroceryCO groceryCO) {
        Item grocery = new Item();
        if(RoleType.ADMIN.equals(roleType)){
            grocery.setName(groceryCO.getName());
            grocery.setInventory(groceryCO.getInventory());
            grocery.setPrice(groceryCO.getPrice());
            grocery = itemRepository.save(grocery);
        }
        return grocery.getId();
    }

    @Override
    public List<GroceryVO> getAll(RoleType roleType) {
        List<GroceryVO> groceryVOS = new ArrayList<>();
        if(RoleType.ADMIN.equals(roleType)){
            groceryVOS = itemRepository.findAll().stream().map(GroceryVO::new).collect(Collectors.toList());
        }
        return groceryVOS;
    }

    @Override
    public void delete(Long id, RoleType roleType) {
        if(RoleType.ADMIN.equals(roleType)) {
            Item grocery = findById(id);
            itemRepository.delete(grocery);
        }
    }

    @Override
    public void update(Long id, RoleType roleType, GroceryUpdateCO groceryUpdateCO) {
        if(RoleType.ADMIN.equals(roleType)) {
            Item grocery = findById(id);
            grocery.setId(id);
            grocery.setName(groceryUpdateCO.getName());
            grocery.setPrice(groceryUpdateCO.getPrice());
            itemRepository.save(grocery);
        }
    }

    @Override
    public List<GroceryVO> getAllAvailableGrocery(RoleType roleType) {
        List<GroceryVO> groceryVOS = new ArrayList<>();
        if(RoleType.USER.equals(roleType)) {
            groceryVOS = itemRepository.findAll().stream().filter(grocery -> grocery.getInventory() >= 1).map(GroceryVO::new).collect(Collectors.toList());
        }
        return groceryVOS;
    }

    @Override
    public Long orderGrocery(RoleType roleType, OrderCO orderCO) {
        OrderData orderData = new OrderData();
        List<Item> items = new ArrayList<>();
        Double totalPrice = 0.0;
        if(RoleType.USER.equals(roleType)) {
            UserData userData = userRepository.findById(orderCO.getUserId()).orElseThrow(() ->
                    new IllegalArgumentException("user not found for the given id"));
            for(Map.Entry<String, Integer> map: orderCO.getQuantity().entrySet()){
                Long itemId = Long.parseLong(map.getKey());
                Integer quantity = map.getValue();
                Item item = findById(itemId);
                if(quantity > 1){
                    totalPrice += (quantity * item.getPrice());
                } else {
                    totalPrice += item.getPrice();
                }
                item.setInventory(item.getInventory()-quantity);
                items.add(item);
            }
            itemRepository.saveAll(items);
            orderData.setTotalPrice(totalPrice);
            orderData.setUserData(userData);
            orderData = orderRepository.save(orderData);
            saveOrderItemMapping(orderCO.getQuantity(), orderData.getId());
        }
        return orderData.getId();
    }

    private void saveOrderItemMapping(Map<String, Integer> items, Long orderId){
        List<OrderItemMapping> orderItemMappings = new ArrayList<>();
        for(Map.Entry<String, Integer> map: items.entrySet()) {
            Long itemId = Long.parseLong(map.getKey());;
            Integer quantity = map.getValue();
            OrderItemMapping orderItemMapping = new OrderItemMapping(orderId, itemId, quantity);
            orderItemMappings.add(orderItemMapping);
        }
        orderItemMappingRepository.saveAll(orderItemMappings);
    }

    private Item findById(Long id){
        return itemRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Item not found for the given id"));
    }

}