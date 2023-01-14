package com.spring.winter.market.api.dtos;

import java.math.BigDecimal;

public class OrderItemDto {

    private Long id;
    private String productTitle;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;

    public OrderItemDto() {
    }

    public OrderItemDto(Long id, String productTitle, int quantity, BigDecimal pricePerProduct, BigDecimal price) {
        this.id = id;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.pricePerProduct = pricePerProduct;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPricePerProduct() {
        return pricePerProduct;
    }

    public void setPricePerProduct(BigDecimal pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
