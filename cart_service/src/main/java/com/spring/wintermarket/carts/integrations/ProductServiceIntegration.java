package com.spring.wintermarket.carts.integrations;

import com.spring.winter.market.api.dtos.ProductDto;
import com.spring.winter.market.api.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final WebClient productServiceWebClient;

    public ProductDto getProductById(Long id){
        ProductDto productDto = productServiceWebClient.get()
                .uri("/api/v1/products/" + id)
                .retrieve()
                .onStatus( // на каждый статус можно создать ексцепшн, можно чтото вытащить из ответа микросервиса и добавить в сообщение
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Товар не найден в продуктовом МС"))
                        )
                .bodyToMono(ProductDto.class)
                .block();

        return productDto;
    }


//    public void clearUserCart(String username){
//        cartServiceWebClient.get()
//                .uri("/api/v1/cart/0/clear")
//                .header("username", username)
//                .retrieve()
//                .toBodilessEntity() // когда не ждем тела ответа (придет ответ без тела)
//                .block
//    }
}
