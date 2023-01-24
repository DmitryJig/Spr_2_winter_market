package com.spring.wintermarket.core.converters;


import com.spring.winter.market.api.dtos.OrderItemDto;
import com.spring.wintermarket.core.entities.OrderItem;
import com.spring.wintermarket.core.services.OrderService;
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
