package com.spring.wintermarket.dtos;

import com.spring.wintermarket.entities.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private Long id;
    private String productTitle;
    private int quantity;
    private int pricePerProduct;
    private int price;
}
