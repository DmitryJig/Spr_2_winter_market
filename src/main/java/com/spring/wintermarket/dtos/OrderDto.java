package com.spring.wintermarket.dtos;

import com.spring.wintermarket.entities.Order;
import com.spring.wintermarket.entities.User;
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
    private String userName;
    private List<OrderItemDto> orderItemDtos;
    private int totalPrice;
    private String address;
    private String phone;
}
