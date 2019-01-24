package com.txtbravo.txtbravo.repository;

import com.txtbravo.txtbravo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findById(Long id);
}