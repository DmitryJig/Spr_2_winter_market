package com.spring.wintermarket.controllers;

import com.spring.wintermarket.dtos.OrderData;
import com.spring.wintermarket.dtos.OrderDto;
import com.spring.wintermarket.entities.Order;
import com.spring.wintermarket.entities.User;
import com.spring.wintermarket.exceptions.ResourceNotFoundException;
import com.spring.wintermarket.services.OrderService;
import com.spring.wintermarket.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    // показываем список всех заказов пользователя
    @GetMapping
    public List<OrderDto> findAllOrders(Principal principal){
        return orderService
                .findAllOrdersByUserName(principal.getName())
                .stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }

    // создание нового заказа
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal, @RequestBody OrderData orderData){
        User user = userService
                .findByUsername(principal.getName())
                .orElseThrow(()-> new ResourceNotFoundException("User with name: " + principal.getName() + " not found"));
        orderService.createOrder(user, orderData);
    }
}
