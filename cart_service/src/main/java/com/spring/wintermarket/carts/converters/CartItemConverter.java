package com.spring.wintermarket.carts.converters;

import com.spring.winter.market.api.dtos.CartItemDto;
import com.spring.wintermarket.carts.models.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemConverter {

    public CartItemDto entityToDto(CartItem cartItem){

        return CartItemDto.Builder.newBuilder()
                .price(cartItem.getPrice())
                .pricePerProduct(cartItem.getPricePerProduct())
                .quantity(cartItem.getQuantity())
                .productTitle(cartItem.getProductTitle())
                .productId(cartItem.getProductId())
                .build();
    }
}
