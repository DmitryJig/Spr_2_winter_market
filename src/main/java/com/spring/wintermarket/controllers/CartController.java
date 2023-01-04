package com.spring.wintermarket.controllers;

import com.spring.wintermarket.dtos.Cart;
import com.spring.wintermarket.services.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id){
        cartService.add(id);
    }

    // очищаем корзину
    @GetMapping("/clear")
    public void clearCart(){
        cartService.clearCart();
    }

    // удаляем строку из корзины
    @GetMapping("/clear/{id}")
    public void clearCart(@PathVariable Long id){
        cartService.clearCart(id);
    }

    // уменьшаем количество товара в корзине
    @GetMapping("/decrement/{id}")
    public void decrementInCart(@PathVariable Long id){
        cartService.decrementInCart(id);
    }

    @GetMapping
    public Cart getCurrentCart(){
        return cartService.getCurrentCart();
    }

}
