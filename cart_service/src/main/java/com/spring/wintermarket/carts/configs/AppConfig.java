package com.spring.wintermarket.carts.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    // этот бин предназначен для отправки запросов по Rest
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
