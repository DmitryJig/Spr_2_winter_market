package com.spring.winter.market.api.dtos;


import java.math.BigDecimal;

public class CartItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private BigDecimal pricePerProduct = new BigDecimal("0.0");
    private BigDecimal price = new BigDecimal("0.0");

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    @Override
    public String toString() {
        return "CartItemDto{" + "productId=" + productId + ", productTitle='" + productTitle + '\'' + ", quantity=" + quantity + ", pricePerProduct=" + pricePerProduct + ", " +
                "price=" + price + '}';
    }
}
