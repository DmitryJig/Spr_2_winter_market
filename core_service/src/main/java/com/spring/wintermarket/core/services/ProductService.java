package com.spring.wintermarket.core.services;

import com.spring.winter.market.api.dtos.ProductDto;
import com.spring.winter.market.api.exceptions.ResourceNotFoundException;
import com.spring.wintermarket.core.entities.Category;
import com.spring.wintermarket.core.entities.Product;
import com.spring.wintermarket.core.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<Product> findAll(){
        return productRepository.findAll();
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
