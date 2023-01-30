package com.spring.wintermarket.carts.repositories;

import com.spring.wintermarket.carts.models.Cart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class CartRepository {
    private Map<String, Cart> carts;

    @PostConstruct
    public void init(){
        carts = new HashMap<>();
        Cart tempCart = new Cart();
        carts.put(null, tempCart);
    }

    /**
     * если пользователь не зарегистрирован даем ему общую корзину,
     * если он есть в мапе то отдаем его корзину, если нет то создаем ему новую корзину
      */
    public Cart findCartByUsername(String username){
        if (username == null){
            log.info("запрошена общая корзина");
            return carts.get(null);
        }
        if (carts.containsKey(username)){
            log.info("Ищем корзину для пользователя " + username);
            return carts.get(username);
        } else {
            log.info("Создана новая корзина для пользователя " + username);
            Cart newCart = new Cart();
            carts.put(username, newCart);
            return carts.get(username);
        }
    }
}
