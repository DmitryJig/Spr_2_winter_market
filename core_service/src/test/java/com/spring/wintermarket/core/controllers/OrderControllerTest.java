package com.spring.wintermarket.core.controllers;

import com.spring.winter.market.api.dtos.CartDto;
import com.spring.winter.market.api.dtos.CartItemDto;
import com.spring.winter.market.api.dtos.OrderDto;
import com.spring.wintermarket.core.SpringBootTestBase;
import com.spring.wintermarket.core.entities.Order;
import com.spring.wintermarket.core.integrations.CartServiceIntegration;
import com.spring.wintermarket.core.repositories.OrderRepository;
import com.spring.wintermarket.core.services.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class OrderControllerTest extends SpringBootTestBase {

    @Autowired
    WebTestClient webTestClient;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;
    @MockBean
    CartServiceIntegration cartServiceIntegration;

    @Test
    void findAllOrders() {
        Mockito.doReturn(this.createCartDto()) // подменяем поведение метода интеграции с модулем корзины
                .when(cartServiceIntegration)
                .getCurrentCart(null);
        
        orderService.createOrder("TestControllerUsername"); // создаем заказ тестового юзера

        List<OrderDto> orderDtos = webTestClient.get()
                .uri("/api/v1/orders")
                .header("username", "TestControllerUsername")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(OrderDto.class)
                .returnResult()
                .getResponseBody();
        OrderDto orderDtoFromHttp = orderDtos.get(orderDtos.size()-1);

        Assertions.assertEquals("TestControllerUsername", orderDtoFromHttp.getUserName());
    }

    @Test
    void createOrder() {
        CartDto cartDto = this.createCartDto();
        Mockito.doReturn(cartDto) // подменяем поведение метода интеграции с модулем корзины
                .when(cartServiceIntegration)
                .getCurrentCart(null);

        webTestClient.post()
                .uri("/api/v1/orders")
                .header("username", "TestControllerUsername")
                .exchange()
                .expectStatus()
                .isCreated();

        List<Order> orders = orderRepository.findAll();
        Order orderFromDb = orders.get(orders.size()-1); // берем последний элемент из списка заказов
        Assertions.assertEquals("TestControllerUsername", orderFromDb.getUsername());
        Assertions.assertEquals(0, orderFromDb.getTotalPrice().compareTo(cartDto.getTotalPrice()));
    }


}