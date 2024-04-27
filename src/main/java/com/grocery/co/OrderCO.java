package com.grocery.co;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class OrderCO {

    private Map<Long, Integer> quantity;

    @NotNull
    private Long userId;

}