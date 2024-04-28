package com.grocery.co;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GroceryUpdateCO {

    @NotNull
    private String name;

    @NotNull
    private Double price;
}