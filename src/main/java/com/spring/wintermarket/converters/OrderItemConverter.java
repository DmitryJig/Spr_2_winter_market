package com.spring.wintermarket.converters;

import com.spring.wintermarket.dtos.OrderItemDto;
import com.spring.wintermarket.entities.OrderItem;
import com.spring.wintermarket.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemConverter {
    private final OrderService orderService;

    public OrderItemDto entityToDto(OrderItem orderItem){
        return
                new OrderItemDto(
                        orderItem.getId(),
                        orderItem.getProduct().getTitle(),
                        orderItem.getQuantity(),
                        orderItem.getPricePerProduct(),
                        orderItem.getPrice()
                );
    }
}
