package com.spring.wintermarket.services;

import com.spring.wintermarket.dtos.Cart;
import com.spring.wintermarket.dtos.CartItem;
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
        init();
    }

    public void clearCart(Long id){
        List<CartItem> newItems = tempCart.getItems().stream().filter(item-> !item.getProductId().equals(id)).collect(Collectors.toList());
        tempCart.setItems(newItems);
//        tempCart.getItems().removeIf(i-> i.getProductId().equals(id));
    }

    public void decrementInCart(Long id) {
        tempCart.decrement(id);
    }
}
