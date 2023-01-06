package com.spring.wintermarket.dtos;

import com.spring.wintermarket.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private List<OrderItemDto> orderItemDtos;
    private int totalPrice;

    public OrderDto(Order order){
        this.id = order.getId();
        this.totalPrice = order.getTotalPrice();
        this.orderItemDtos = new ArrayList<>();
        order.getItems()
                .forEach(oi -> {
                    orderItemDtos.add(new OrderItemDto(oi));
                });
    }
}
