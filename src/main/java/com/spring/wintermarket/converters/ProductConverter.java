package com.spring.wintermarket.converters;

import com.spring.wintermarket.dtos.ProductDto;
import com.spring.wintermarket.entities.Category;
import com.spring.wintermarket.entities.Product;
import com.spring.wintermarket.exceptions.ResourceNotFoundException;
import com.spring.wintermarket.services.CategoryService;
import com.spring.wintermarket.soap.productsws.ProductWs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductConverter {
    private final CategoryService categoryService;
    public ProductDto entityToDto(Product product){
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice(), product.getCategory().getTitle());
    }

    public Product dtoToEntity(ProductDto productDto){
        Product p = new Product();
        p.setId(productDto.getId());
        p.setTitle(p.getTitle());
        p.setPrice(p.getPrice());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(()-> new ResourceNotFoundException("category not found"));
        p.setCategory(category);
        return p;
    }

    public ProductWs entityToSoap(Product product){
        ProductWs productWs = new ProductWs();
        productWs.setId(product.getId());
        productWs.setTitle(product.getTitle());
        productWs.setPrice(product.getPrice());
        productWs.setCategoryTitle(product.getCategory().getTitle());
        return productWs;
    }
}
