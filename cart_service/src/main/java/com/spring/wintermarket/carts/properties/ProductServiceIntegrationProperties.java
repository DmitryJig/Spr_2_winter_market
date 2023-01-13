package com.spring.wintermarket.carts.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding // это бин и конструктор биндинг значит что поля будут заполняться через конструктор
@ConfigurationProperties(prefix = "integrations.product-service")
@Data
public class ProductServiceIntegrationProperties {
    private String url;
    private Integer readTimeout; // берется из application.yaml, где был дефис будет кэмэл кейс
    private Integer writeTimeout;
    private Integer connectTimeout;
}
