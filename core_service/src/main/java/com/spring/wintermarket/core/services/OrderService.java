package com.spring.wintermarket.core.services;

import com.spring.winter.market.api.dtos.CartDto;
import com.spring.wintermarket.core.entities.Order;
import com.spring.wintermarket.core.entities.OrderItem;
import com.spring.wintermarket.core.integrations.CartServiceIntegration;
import com.spring.wintermarket.core.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartServiceIntegration cartServiceIntegration;
    private final ProductService productService;

    @Transactional
    public void createOrder(String username){

        CartDto cartDto = cartServiceIntegration.getCurrentCart();

        Order order = new Order();
        order.setUsername(username);
        order.setTotalPrice(cartDto.getTotalPrice());
//        order.setAddress(orderData.getAddress());
//        order.setPhone(orderData.getPhone());
        order.setItems(cartDto.getItems().stream().map(
                cartItem -> new OrderItem(
                        productService.findById(cartItem.getProductId()).get(),
                        order,
                        cartItem.getQuantity(),
                        cartItem.getPricePerProduct(),
                        cartItem.getPrice()
                        )
        ).collect(Collectors.toList()));
        orderRepository.save(order);
        cartServiceIntegration.clearCart();
    }

//    public void createOrder(User user, OrderData orderData){
//        Order order = new Order();
//        Cart cart = cartService.getCurrentCart();
//        order.setUser(user);
//        order.setTotalPrice(cart.getTotalPrice());
//        order.setAddress(orderData.getAddress());
//        order.setPhone(orderData.getPhone());
//        List<OrderItem> orderItems = new ArrayList<>();
//        cart.getItems().forEach(cartItem -> {
//            orderItems.add(new OrderItem(productService.findById(cartItem.getProductId()).get(), order, cartItem.getQuantity(), cartItem.getPricePerProduct(), cartItem.getPrice()));
//        });
//        order.setItems(orderItems);
//        orderRepository.save(order);
//        cart.clear();
//    }

    public List<Order> findAllOrdersByUserName(String username){
        return orderRepository.findAllByUsername(username);
    }
}
