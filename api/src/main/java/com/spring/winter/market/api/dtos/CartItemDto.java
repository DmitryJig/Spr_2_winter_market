package com.spring.winter.market.api.dtos;


import java.math.BigDecimal;

public class CartItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;

    public CartItemDto() {
    }

    private CartItemDto(Builder builder) {
        setProductId(builder.productId);
        setProductTitle(builder.productTitle);
        setQuantity(builder.quantity);
        setPricePerProduct(builder.pricePerProduct);
        setPrice(builder.price);
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPricePerProduct() {
        return pricePerProduct;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPricePerProduct(BigDecimal pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }



    @Override
    public String toString() {
        return "CartItemDto{" + "productId=" + productId + ", productTitle='" + productTitle + '\'' + ", quantity=" + quantity + ", pricePerProduct=" + pricePerProduct + ", " +
                "price=" + price + '}';
    }

    public static final class Builder {
        private Long productId;
        private String productTitle;
        private int quantity;
        private BigDecimal pricePerProduct;
        private BigDecimal price;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder productId(Long val) {
            productId = val;
            return this;
        }

        public Builder productTitle(String val) {
            productTitle = val;
            return this;
        }

        public Builder quantity(int val) {
            quantity = val;
            return this;
        }

        public Builder pricePerProduct(BigDecimal val) {
            pricePerProduct = val;
            return this;
        }

        public Builder price(BigDecimal val) {
            price = val;
            return this;
        }

        public CartItemDto build() {
            return new CartItemDto(this);
        }
    }
}
