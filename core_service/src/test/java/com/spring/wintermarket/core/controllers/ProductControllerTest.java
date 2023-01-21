package com.spring.wintermarket.core.controllers;

import com.spring.winter.market.api.dtos.ProductDto;
import com.spring.wintermarket.core.SpringBootTestBase;
import com.spring.wintermarket.core.converters.ProductConverter;
import com.spring.wintermarket.core.entities.Product;
import com.spring.wintermarket.core.repositories.CategoryRepository;
import com.spring.wintermarket.core.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

class ProductControllerTest extends SpringBootTestBase {

    @Autowired
    WebTestClient webTestClient;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductConverter productConverter;


    @Test
    void testFindAllProducts() {
        List<Product> allProductsByRepo = productRepository.findAll();

        // метод контроллера возвращает объект Page<ProductDto>
        webTestClient.get()
                .uri("/api/v1/products")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.content")
                .isNotEmpty()
                .jsonPath("$.content.length()")
                .isEqualTo(allProductsByRepo.size())
                .jsonPath("$.content[0].id")
                .isEqualTo(allProductsByRepo.get(0).getId())
                .jsonPath("$.content[0].title")
                .isEqualTo(allProductsByRepo.get(0).getTitle())
                .jsonPath("$.content[0].price")
                .isEqualTo(allProductsByRepo.get(0).getPrice().doubleValue()); // правильно ли BigDecimal переводить для сравнения в Double
    }

    @Test
    void testFindProductById() {
        Product testProduct = this.createProduct();
        ProductDto productDtoByHttp =
                webTestClient.get()
                        .uri("/api/v1/products/" + testProduct.getId())
                        .exchange()
                        .expectStatus().isOk()
                        .expectBody(ProductDto.class)
                        .returnResult()
                        .getResponseBody();
        Assertions.assertNotNull(productDtoByHttp.getId());
        Assertions.assertEquals(testProduct.getId(), productDtoByHttp.getId());
        Assertions.assertEquals(testProduct.getTitle(), productDtoByHttp.getTitle());
        Assertions.assertEquals(testProduct.getCategory().getTitle(), productDtoByHttp.getCategoryTitle());
    }

    @Test
    void testCreateNewProduct() {
        ProductDto testProductDto = new ProductDto();
        testProductDto.setTitle("testCreatedProduct");
        testProductDto.setPrice(BigDecimal.valueOf(5000000));
        testProductDto.setCategoryTitle("Others");

        ProductDto productDtoByHttp =
                webTestClient.post()
                .uri("/api/v1/products/")
                .body(BodyInserters.fromValue(testProductDto))
                        .exchange()
                        .expectStatus().isOk()
                        .expectBody(ProductDto.class)
                        .returnResult()
                        .getResponseBody();

        Assertions.assertNotNull(productDtoByHttp);
        Assertions.assertNotNull(productDtoByHttp.getId());
        Assertions.assertEquals(testProductDto.getTitle(), productDtoByHttp.getTitle());
        Assertions.assertEquals(0, productDtoByHttp.getPrice().compareTo(testProductDto.getPrice()));
        Assertions.assertEquals(testProductDto.getCategoryTitle(), productDtoByHttp.getCategoryTitle());
    }

    @Test
    void testDeleteProduct(){
        Product product = this.createProduct();
        Long id = product.getId();

        webTestClient.delete()
                .uri("/api/v1/products/" + id)
                .exchange()
                .expectStatus().isOk();

        Product productAfterDelete = productRepository.findById(id).orElse(null);
        Assertions.assertNull(productAfterDelete);
    }

    @Transactional
    public Product createProduct(){
        Product product = new Product();
        product.setTitle("TestCar");
        product.setPrice(BigDecimal.valueOf(1000000));
        product.setCategory(categoryRepository.findByTitle("Others").get());
        return productRepository.save(product);
    }
}