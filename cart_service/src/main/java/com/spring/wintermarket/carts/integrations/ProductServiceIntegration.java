package com.spring.wintermarket.carts.integrations;

import com.spring.winter.market.api.dtos.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;

    @Value("${integration.getProductByIdEndpoint}")
    private String getProductByIdEndpoint;

    public Optional<ProductDto> getProductById(Long id){
        return Optional.ofNullable(restTemplate.getForObject(getProductByIdEndpoint + id, ProductDto.class));
    }
}
