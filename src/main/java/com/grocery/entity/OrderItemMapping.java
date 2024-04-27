package com.grocery.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@IdClass(OrderItemMapping.OrderItemMappingPK.class)
@NoArgsConstructor
public class OrderItemMapping {

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private OrderData orderData;

    @ManyToOne
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    private Item item;


    @Id
    @Column(nullable = false, name = "item_id")
    private Long itemId;

    @Id
    @Column(nullable = false, name = "order_id")
    private Long orderId;


    private int itemCount;

    public OrderItemMapping(Long orderId, Long itemId, int itemCount) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.itemCount = itemCount;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemMappingPK implements Serializable {
        private Long orderId;
        private Long itemId;
    }
}