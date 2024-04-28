package com.grocery.co;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GroceryCO {

    @NotNull
    private String name;

    @NotNull
    private Double price;

    @NotNull
    private int inventory;
}