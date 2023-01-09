package com.spring.wintermarket.services;

import com.spring.wintermarket.dtos.OrderData;
import com.spring.wintermarket.entities.Order;
import com.spring.wintermarket.entities.OrderItem;
import com.spring.wintermarket.entities.User;
import com.spring.wintermarket.models.Cart;
import com.spring.wintermarket.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final ProductService productService;

    public void createOrder(User user, OrderData orderData){
        Order order = new Order();
        Cart cart = cartService.getCurrentCart();
        order.setUser(user);
        order.setTotalPrice(cart.getTotalPrice());
        order.setAddress(orderData.getAddress());
        order.setPhone(orderData.getPhone());
        List<OrderItem> orderItems = new ArrayList<>();
        cart.getItems().forEach(cartItem -> {
            orderItems.add(new OrderItem(productService.findById(cartItem.getProductId()).get(), order, cartItem.getQuantity(), cartItem.getPricePerProduct(), cartItem.getPrice()));
        });
        order.setItems(orderItems);
        orderRepository.save(order);
        cart.clear();
    }

    public List<Order> findAllOrdersByUserName(String username){
        return orderRepository.findAllByUserUsername(username);
    }
}
