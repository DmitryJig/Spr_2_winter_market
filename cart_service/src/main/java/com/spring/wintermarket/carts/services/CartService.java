package com.spring.wintermarket.carts.services;

import com.spring.winter.market.api.dtos.ProductDto;
import com.spring.winter.market.api.exceptions.ResourceNotFoundException;
import com.spring.wintermarket.carts.integrations.ProductServiceIntegration;
import com.spring.wintermarket.carts.models.Cart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Cart tempCart;

    @PostConstruct
    public void init(){
        tempCart = new Cart();
    }

    public Cart getCurrentCart(){
        return tempCart;
    }

    public void add(Long productId){
        ProductDto product = productServiceIntegration.getProductById(productId);

        tempCart.add(product);
    }

    public void clearCart(){
        tempCart.clear();
    }

    public void remove(Long productId){
        tempCart.remove(productId);
    }


}
