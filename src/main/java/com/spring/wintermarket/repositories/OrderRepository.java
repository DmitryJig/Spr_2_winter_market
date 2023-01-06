package com.spring.wintermarket.repositories;

import com.spring.wintermarket.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUserUsername(String userUsername);
}
