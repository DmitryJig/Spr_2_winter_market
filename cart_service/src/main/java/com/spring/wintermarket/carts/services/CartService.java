package com.spring.wintermarket.carts.services;

import com.spring.winter.market.api.dtos.ProductDto;
import com.spring.wintermarket.carts.integrations.ProductServiceIntegration;
import com.spring.wintermarket.carts.models.Cart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

/**
 * чтобы почистить кэш редиса в командной строке набрать
 * redis-cli flushall async
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;

    public Cart getCurrentCart(String uuid) {
        String targetUuid = cartPrefix + uuid;
        if (!redisTemplate.hasKey(targetUuid)) {
            redisTemplate.opsForValue().set(targetUuid, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(targetUuid);
    }

    public void add(String uuid, Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        log.info("Добавление товара в корзину с uuid " + uuid);
        execute(uuid, cart -> cart.add(product));
    }

    public void clearCart(String uuid) {
        execute(uuid, Cart::clear);
    }

    public void remove(String uuid, Long productId) {
        execute(uuid, cart -> cart.remove(productId));
    }

    private void execute(String uuid, Consumer<Cart> operation){
        Cart cart = getCurrentCart(uuid);
        operation.accept(cart);
        redisTemplate.opsForValue().set(cartPrefix + uuid, cart);
    }
}
