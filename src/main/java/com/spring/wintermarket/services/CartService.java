package com.spring.wintermarket.services;

import com.spring.wintermarket.dtos.Cart;
import com.spring.wintermarket.entities.Product;
import com.spring.wintermarket.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private Cart tempCart;

    @PostConstruct
    public void init(){
        tempCart = new Cart();
    }

    public Cart getCurrentCart(){
        return tempCart;
    }

    public void add(Long productId){
        Product product = productService
                .findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Не удается добавить продукт с id: " + productId + " в корзину. Продукт не найден"));
        tempCart.add(product);
    }
}
