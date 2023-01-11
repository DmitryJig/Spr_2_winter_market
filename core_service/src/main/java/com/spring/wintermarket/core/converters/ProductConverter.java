package com.spring.wintermarket.core.converters;

import com.spring.winter.market.api.dtos.ProductDto;
import com.spring.winter.market.api.exceptions.ResourceNotFoundException;
import com.spring.wintermarket.core.entities.Category;
import com.spring.wintermarket.core.entities.Product;
import com.spring.wintermarket.core.services.CategoryService;
import com.spring.wintermarket.core.soap.productsws.ProductWs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductConverter {
    private final CategoryService categoryService;
    public ProductDto entityToDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(productDto.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setCategoryTitle(product.getCategory().getTitle());

        return productDto;
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
