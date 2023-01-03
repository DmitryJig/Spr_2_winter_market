package com.spring.wintermarket.controllers;

import com.spring.wintermarket.dtos.ProductDto;
import com.spring.wintermarket.entities.Product;
import com.spring.wintermarket.exceptions.AppError;
import com.spring.wintermarket.exceptions.ResourceNotFoundException;
import com.spring.wintermarket.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> findAllProducts(){
        return productService.findAll().stream().map(p-> new ProductDto(p.getId(), p.getTitle(), p.getPrice())).toList();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> findProductById(@PathVariable Long id){
//        Optional<Product> productOptional = productService.findById(id);
//        if (!productOptional.isPresent()){
//            ResponseEntity<AppError> error =
//                    new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Продукт не найденб id: " + id), HttpStatus.NOT_FOUND);
//            return error;
//        }
//        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id){
        Product p = productService
                .findById(id)
                .orElseThrow(() ->new ResourceNotFoundException("Продукт не найденб id: " + id));
        return new ProductDto(p.getId(), p.getTitle(), p.getPrice());
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id){
        productService.deleteById(id);
    }
}
