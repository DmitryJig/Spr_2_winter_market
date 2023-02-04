package com.spring.wintermarket.core.services;

import com.spring.winter.market.api.dtos.CartDto;
import com.spring.wintermarket.core.SpringBootTestBase;
import com.spring.wintermarket.core.entities.Order;
import com.spring.wintermarket.core.integrations.CartServiceIntegration;
import com.spring.wintermarket.core.repositories.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;


class OrderServiceTest extends SpringBootTestBase {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @MockBean
    CartServiceIntegration cartServiceIntegration;

    @Test
    void createOrder() {
        CartDto cartDto = this.createCartDto();
        Mockito.doReturn(cartDto) // подменяем поведение метода интеграции с модулем корзины
                .when(cartServiceIntegration)
                .getCurrentCart(null);
        orderService.createOrder("testServiceUsername");
        List<Order> allOrderByRepo = orderRepository.findAll();
        Order lastOrderByRepo = allOrderByRepo.get(allOrderByRepo.size()-1);
        Assertions.assertNotNull(lastOrderByRepo.getId());
        Assertions.assertEquals("testServiceUsername", lastOrderByRepo.getUsername());
    }

    @Test
    void findAllOrdersByUserName() {
        Mockito.doReturn(this.createCartDto()) // подменяем поведение метода интеграции с модулем корзины
                .when(cartServiceIntegration)
                .getCurrentCart(null);

        orderService.createOrder("Testusername"); // создаем заказ тестового юзера
        List<Order> ordersByRepo = orderRepository.findByUsername("Testusername");
        Order lastOrderByRepo = ordersByRepo.get(ordersByRepo.size() - 1);
        Assertions.assertEquals("Testusername", lastOrderByRepo.getUsername());
    }
}