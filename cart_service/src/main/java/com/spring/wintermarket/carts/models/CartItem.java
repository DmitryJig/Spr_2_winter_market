package com.spring.wintermarket.carts.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Long productId;
    private String productTitle;
    private int quantity;
    private BigDecimal pricePerProduct = new BigDecimal("0.0");
    private BigDecimal price = new BigDecimal("0.0");

    public void changeQuantity(int delta){
        quantity += delta;
        price = pricePerProduct.multiply(BigDecimal.valueOf(quantity));
    }
}
