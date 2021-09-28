package com.unipi.ipap.orderservice.repository;

import com.unipi.ipap.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}