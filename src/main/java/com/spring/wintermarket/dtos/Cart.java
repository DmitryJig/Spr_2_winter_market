package com.spring.wintermarket.dtos;

import com.spring.wintermarket.entities.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
public class Cart {
    private List<CartItem> items;
    private int totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems(){
        return Collections.unmodifiableList(items);
    }


    public void add(Product product){
        Optional<CartItem> optionalItem = items.stream().filter(i -> i.getProductId().equals(product.getId())).findFirst();
        if (optionalItem.isPresent()){
            CartItem item = optionalItem.get();
            item.setQuantity(item.getQuantity() + 1);
            item.setPrice(item.getPrice() + product.getPrice());
        } else {
            items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        }
        recalculate();
    }

    public void decrement(Long id){
        Optional<CartItem> optionalItem = items.stream().filter(i -> i.getProductId().equals(id)).findFirst();
        if (optionalItem.isPresent()) {
            CartItem item = optionalItem.get();
            item.setQuantity(item.getQuantity() - 1);
            item.setPrice(item.getPrice() - item.getPricePerProduct());
        }
        recalculate();
    }

    private void recalculate(){
        totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getPrice();
        }
    }
}
