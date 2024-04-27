package com.grocery.co;

import com.grocery.enums.RoleType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GroceryUpdateCO {

    private RoleType roleType;

    @NotNull
    private String name;

    @NotNull
    private Double price;
}