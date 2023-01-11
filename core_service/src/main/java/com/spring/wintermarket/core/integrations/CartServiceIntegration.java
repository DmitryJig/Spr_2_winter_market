package com.spring.wintermarket.core.integrations;

import com.spring.winter.market.api.dtos.CartDto;
import com.spring.winter.market.api.dtos.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final RestTemplate restTemplate;

    @Value("${integration.getCurrentCartEndpoint}")
    private String getCurrentCartEndpoint;

    @Value("${integration.clearCartEndpoint}")
    private String clearCartEndpoint;

    public Optional<CartDto> getCurrentCart(){
        return Optional.ofNullable(restTemplate.getForObject(getCurrentCartEndpoint, CartDto.class));
    }

    public void clearCart(){
        restTemplate.put(clearCartEndpoint, null);
    }
}
