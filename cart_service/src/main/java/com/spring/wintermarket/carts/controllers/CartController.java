package com.spring.wintermarket.carts.controllers;

import com.spring.winter.market.api.dtos.CartDto;
import com.spring.winter.market.api.dtos.StringResponse;
import com.spring.wintermarket.carts.converters.CartConverter;
import com.spring.wintermarket.carts.models.Cart;
import com.spring.wintermarket.carts.services.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/generate_uuid")
    public StringResponse generateUuid(){
        StringResponse response = new StringResponse(UUID.randomUUID().toString());
        log.info("сгенерирован uuid " + response.getValue());
        return response;
    }

    @GetMapping("/{uuid}/add/{id}")
    public void addToCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid, @PathVariable Long id){
        String targetUuid = getCartUuid(username, uuid);
        cartService.add(targetUuid, id);
    }

    // очищаем корзину
    @PutMapping("/{uuid}/clear")
    @ResponseStatus(HttpStatus.OK)
    public void clearCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid){
        String targetUuid = getCartUuid(username, uuid);
        cartService.clearCart(targetUuid);
    }

    // удаляем строку из корзины
    @GetMapping("/{uuid}/remove/{id}")
    public void removeFromCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid, @PathVariable Long id){
        String targetUuid = getCartUuid(username, uuid);
        cartService.remove(targetUuid, id);
    }

    @GetMapping("/{uuid}")
    public CartDto getCurrentCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid){
        String targetUuid = getCartUuid(username, uuid);
        Cart cart = cartService.getCurrentCart(targetUuid);
        return cartConverter.entityToDto(cart);
    }

    private  String getCartUuid(String username, String uuid){
        if (username != null){
            return username;
        }
        return uuid;
    }
}
