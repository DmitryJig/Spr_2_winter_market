package com.spring.wintermarket.core.controllers;

import com.spring.winter.market.api.dtos.ProductDto;
import com.spring.winter.market.api.exceptions.ResourceNotFoundException;
import com.spring.wintermarket.core.converters.ProductConverter;
import com.spring.wintermarket.core.entities.Product;
import com.spring.wintermarket.core.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping
    public List<ProductDto> findAllProducts(){
        return productService.findAll().stream().map(productConverter::entityToDto).toList();
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id){
        Product product = productService
                .findById(id)
                .orElseThrow(() ->new ResourceNotFoundException("Продукт не найденб id: " + id));
        return productConverter.entityToDto(product);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto productDto){
        Product product = productService.createNewProduct(productDto);
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id){
        productService.deleteById(id);
    }
}
