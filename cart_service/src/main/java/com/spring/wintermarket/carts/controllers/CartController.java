package com.spring.wintermarket.carts.controllers;

import com.spring.winter.market.api.dtos.CartDto;
import com.spring.wintermarket.carts.converters.CartConverter;
import com.spring.wintermarket.carts.models.Cart;
import com.spring.wintermarket.carts.services.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/add/{id}")
    public void addToCart(@RequestHeader(required = false) String username, @PathVariable Long id){
        cartService.add(username, id);
    }

    // очищаем корзину
    @PutMapping("/clear")
    @ResponseStatus(HttpStatus.OK)
    public void clearCart(@RequestHeader(required = false) String username){
        cartService.clearCart(username);
    }

    // удаляем строку из корзины
    @GetMapping("/remove/{id}")
    public void removeFromCart(@RequestHeader(required = false) String username, @PathVariable Long id){
        cartService.remove(username, id);
    }

    @GetMapping
    public CartDto getCurrentCart(@RequestHeader(required = false) String username){
        log.info("Запрошена корзина для пользователя " + username);
        Cart cart = cartService.getCurrentCart(username);
        return cartConverter.entityToDto(cart);
    }

}
