package com.spring.wintermarket.core;

import com.spring.winter.market.api.dtos.CartDto;
import com.spring.winter.market.api.dtos.CartItemDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Slf4j
public abstract class SpringBootTestBase {
    /**
     * Метод для создания ДТО корзины заказов
     * @return CartDto
     */
    public CartDto createCartDto() {
        CartDto cartDto = new CartDto();
        List<CartItemDto> itemDtos = new ArrayList<>();
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProductId(Long.valueOf(1L));
        cartItemDto.setProductTitle("milk");
        cartItemDto.setQuantity(2);
        cartItemDto.setPricePerProduct(BigDecimal.valueOf(80.0));
        cartItemDto.setPrice(cartItemDto.getPricePerProduct().multiply(BigDecimal.valueOf(cartItemDto.getQuantity())));
        itemDtos.add(cartItemDto);
        cartDto.setItems(itemDtos);
        BigDecimal cartTotalPrice = BigDecimal.valueOf(0);
        for (CartItemDto item: itemDtos) {
            cartTotalPrice = cartTotalPrice.add(item.getPrice());
        }
        cartDto.setTotalPrice(cartTotalPrice);
        return cartDto;
    }
}
