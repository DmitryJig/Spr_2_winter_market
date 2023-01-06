package com.spring.wintermarket.services;

import com.spring.wintermarket.models.Cart;
import com.spring.wintermarket.models.CartItem;
import com.spring.wintermarket.entities.Product;
import com.spring.wintermarket.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
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

    public void clearCart(){
        tempCart.clear();
    }

    public void remove(Long productId){
        tempCart.remove(productId);
    }


}
