package com.spring.wintermarket.core.repositories;

import com.spring.wintermarket.core.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUsername(String username);
}
