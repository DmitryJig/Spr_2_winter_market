package com.spring.wintermarket.core.services;

import com.spring.winter.market.api.dtos.ProductDto;
import com.spring.wintermarket.core.SpringBootTestBase;
import com.spring.wintermarket.core.entities.Product;
import com.spring.wintermarket.core.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

class ProductServiceTest extends SpringBootTestBase {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;


    @Test
    void findAll() {
        List<Product> productsFromRepo = productRepository.findAll().subList(0,3);

        Page<Product> pageProducts = productService.findAll(null, null,null, 1);
        List<Product> productsFromService = pageProducts.getContent().subList(0, 3);
        Assertions.assertNotNull(productsFromService.size());
        Assertions.assertNotNull(productsFromService.get(0).getId());
        Assertions.assertEquals(productsFromRepo.get(0), productsFromService.get(0));
        Assertions.assertIterableEquals(productsFromRepo, productsFromService);
    }

    @Test
    void findById() {
        Product demoProduct = new Product();
        demoProduct.setTitle("lobster");
        demoProduct.setPrice(BigDecimal.valueOf(123));
        demoProduct.setCategory(categoryService.findByTitle("Food").get());
        Product savedProduct = productRepository.save(demoProduct);

        Assertions.assertEquals("lobster", savedProduct.getTitle());
        Assertions.assertEquals(0, savedProduct.getPrice().compareTo(demoProduct.getPrice()));
        Assertions.assertNotNull(savedProduct.getId());


    }

    @Test
    void createNewProduct() {
        ProductDto productDto = this.createProductDto();

        Product createdProduct = productService.createNewProduct(productDto);

        Assertions.assertNotNull(createdProduct);
        Assertions.assertNotNull(createdProduct.getId());
        Assertions.assertEquals(productDto.getTitle(), createdProduct.getTitle());

        productService.deleteById(createdProduct.getId()); // Почистили за собой
    }

    public ProductDto createProductDto(){
        ProductDto productDto = new ProductDto();
        productDto.setTitle("TestCar");
        productDto.setPrice(BigDecimal.valueOf(1000000));
        productDto.setCategoryTitle("Others");
        return productDto;
    }
}