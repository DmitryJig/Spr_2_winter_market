package com.spring.wintermarket.core.controllers;

import com.spring.winter.market.api.dtos.OrderData;
import com.spring.winter.market.api.dtos.OrderDto;
import com.spring.winter.market.api.exceptions.ResourceNotFoundException;
import com.spring.wintermarket.core.converters.OrderConverter;
import com.spring.wintermarket.core.entities.User;
import com.spring.wintermarket.core.services.OrderService;
import com.spring.wintermarket.core.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@CrossOrigin("*")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final OrderConverter orderConverter;

    // показываем список всех заказов пользователя
    @GetMapping
    public List<OrderDto> findAllOrders(Principal principal){
        return orderService
                .findAllOrdersByUserName(principal.getName())
                .stream()
                .map(orderConverter::entityToDto)
                .collect(Collectors.toList());
    }

//     создание нового заказа
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal, @RequestBody OrderData orderData){
        User user = userService
                .findByUsername(principal.getName())
                .orElseThrow(()-> new ResourceNotFoundException("User with name: " + principal.getName() + " not found"));
        orderService.createOrder(user, orderData);
    }

    // создание нового заказа
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void createOrder(Principal principal, @RequestBody OrderData orderData){
//        User user = userService
//                .findByUsername(principal.getName())
//                .orElseThrow(()-> new ResourceNotFoundException("User with name: " + principal.getName() + " not found"));
//        orderService.createOrder(user, orderData);
//    }
}
