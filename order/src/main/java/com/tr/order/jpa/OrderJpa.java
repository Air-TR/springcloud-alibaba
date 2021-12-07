package com.tr.order.jpa;

import com.tr.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderJpa extends JpaRepository<Order, Integer> {

    List<Order> findByCustomerId(Integer customerId);

}
