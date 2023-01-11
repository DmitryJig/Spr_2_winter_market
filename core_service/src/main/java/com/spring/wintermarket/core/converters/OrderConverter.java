package com.spring.wintermarket.core.converters;

import com.spring.winter.market.api.dtos.OrderDto;
import com.spring.winter.market.api.dtos.OrderItemDto;
import com.spring.wintermarket.core.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;

    public OrderDto entityToDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        List<OrderItemDto> orderItemDtos = order.getItems().stream().map(orderItemConverter::entityToDto).collect(Collectors.toList());
        orderDto.setOrderItemDtos(orderItemDtos);
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setAddress(order.getAddress());
        orderDto.setPhone(order.getPhone());
        return orderDto;
    }
}
