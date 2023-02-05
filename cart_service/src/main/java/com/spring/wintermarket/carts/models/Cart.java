package com.spring.wintermarket.carts.models;

import com.spring.winter.market.api.dtos.ProductDto;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@ToString
public class Cart {
    private List<CartItem> items;
    private BigDecimal totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
        this.totalPrice = BigDecimal.ZERO;
    }

    public void add(ProductDto product) {
        for (CartItem item : items) {
            if (product.getId().equals(item.getProductId())) {
                item.changeQuantity(1);
                recalculate();
                return;
            }
        }
        items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        recalculate();
    }

    public void mergeCart(Cart addedCart){
        addedCart.getItems().forEach(addedItem -> {
            boolean isProductExists = false;
            for (CartItem item : items){
                if (addedItem.getProductId().equals(item.getProductId())){
                    item.changeQuantity(addedItem.getQuantity());
                    isProductExists = true;
                    break;
                }
            }
            if (!isProductExists){
                items.add(addedItem);
            }
            recalculate();
        });
    }

    public void remove(Long productId) {
        if (items.removeIf(item -> item.getProductId().equals(productId))) {
            recalculate();
        }
    }

    public void clear() {
        items.clear();
        totalPrice = BigDecimal.ZERO;
    }

    private void recalculate() {
        totalPrice = BigDecimal.ZERO;
        for (CartItem item : items) {
            totalPrice = totalPrice.add(item.getPrice());
        }
    }
}
