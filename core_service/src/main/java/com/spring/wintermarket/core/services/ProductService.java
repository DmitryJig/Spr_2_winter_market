package com.spring.wintermarket.core.services;

import com.spring.winter.market.api.dtos.ProductDto;
import com.spring.winter.market.api.exceptions.ResourceNotFoundException;
import com.spring.wintermarket.core.entities.Category;
import com.spring.wintermarket.core.entities.Product;
import com.spring.wintermarket.core.repositories.ProductRepository;
import com.spring.wintermarket.core.repositories.specifications.ProductSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public Page<Product> findAll(BigDecimal minPrice, BigDecimal maxPrice, String partTitle, int page){
        Specification<Product> specification = Specification.where(null); // null значит спецификация ничего не проверяет
        if (minPrice != null){
            specification = specification.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null){
            specification = specification.and(ProductSpecifications.priceLessOrEqualsThan(maxPrice));
        }
        if (partTitle != null){
            specification = specification.and(ProductSpecifications.titleLike(partTitle));
        }
        return productRepository.findAll(specification, PageRequest.of(page - 1, 5));
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public Product createNewProduct(ProductDto productDto){
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService
                .findByTitle(productDto.getCategoryTitle())
                .orElseThrow(()-> new ResourceNotFoundException("category not found"));
        product.setCategory(category);
        productRepository.save(product);
        return product;
    }

    public Product findByTitle(String title){
        Product product = productRepository
                .findByTitle(title)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        return product;
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }
}
