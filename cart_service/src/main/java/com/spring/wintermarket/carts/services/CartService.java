package com.spring.wintermarket.carts.services;

import com.spring.winter.market.api.dtos.ProductDto;
import com.spring.winter.market.api.exceptions.ResourceNotFoundException;
import com.spring.wintermarket.carts.integrations.ProductServiceIntegration;
import com.spring.wintermarket.carts.models.Cart;
import com.spring.wintermarket.carts.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private final CartRepository cartRepository;

//    private Cart tempCart;
//
//    @PostConstruct
//    public void init(){
//        tempCart = new Cart();
//    }

    public Cart getCurrentCart(String username){
        return cartRepository.findCartByUsername(username);
    }

    public void add(String username, Long productId){
        ProductDto product = productServiceIntegration.getProductById(productId);
        cartRepository.findCartByUsername(username).add(product);
    }

    public void clearCart(String username){
        cartRepository.findCartByUsername(username).clear();
    }

    public void remove(String username, Long productId){
        cartRepository.findCartByUsername(username).remove(productId);
    }


}
