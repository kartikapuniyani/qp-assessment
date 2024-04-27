package com.grocery.vo;

import com.grocery.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroceryVO implements Serializable {

    private Long id;
    private String name;
    private Double price;
    private int inventory;

    public GroceryVO(Item grocery){
        this.id = grocery.getId();
        this.name = grocery.getName();
        this.price = grocery.getPrice();
        this.inventory = grocery.getInventory();
    }

}