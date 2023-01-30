package com.spring.wintermarket.core.controllers;

import com.spring.winter.market.api.dtos.ProductDto;
import com.spring.winter.market.api.exceptions.ResourceNotFoundException;
import com.spring.wintermarket.core.converters.ProductConverter;
import com.spring.wintermarket.core.entities.Product;
import com.spring.wintermarket.core.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping
    public Page<ProductDto> findAllProducts(
            @RequestParam(name = "pageIndex", defaultValue = "1") Integer page, //todo p
            @RequestParam(name = "min_price", required = false) BigDecimal minPrice,
            @RequestParam(name = "max_price", required = false) BigDecimal maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (page < 1){
            page = 1;
        }
        return productService.findAll(minPrice, maxPrice, titlePart, page).map(productConverter::entityToDto);
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
