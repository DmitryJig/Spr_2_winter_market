package com.spring.wintermarket.core.repositories.specifications;


import com.spring.wintermarket.core.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecifications {

    public static Specification<Product> priceGreaterOrEqualsThan(BigDecimal price) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> priceLessOrEqualsThan(BigDecimal price){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> titleLike(String titlePart){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
    }
}
