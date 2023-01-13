package com.spring.wintermarket.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding // это бин и конструктор биндинг значит что поля будут заполняться через конструктор
@ConfigurationProperties(prefix = "integrations.cart-service")
@Data
public class CartServiceIntegrationProperties {
    private String url;
    private Integer readTimeout; // берется из application.yaml, где был дефис будет кэмэл кейс
    private Integer writeTimeout;
    private Integer connectTimeout;
}
