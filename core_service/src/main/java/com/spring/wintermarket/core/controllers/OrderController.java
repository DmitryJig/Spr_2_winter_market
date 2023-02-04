package com.spring.wintermarket.core.controllers;

import com.spring.winter.market.api.dtos.OrderDto;
import com.spring.wintermarket.core.converters.OrderConverter;
import com.spring.wintermarket.core.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;

//     показываем список всех заказов пользователя
    @GetMapping
    public List<OrderDto> getUserOrders(@RequestHeader String username){
        return orderService
                .findAllOrdersByUserName(username)
                .stream()
                .map(orderConverter::entityToDto)
                .collect(Collectors.toList());
    }

//     создание нового заказа
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username /*,@RequestBody OrderData orderData*/){
        log.info("Создание заказа пользователем " + username);
        orderService.createOrder(username);
    }


}
