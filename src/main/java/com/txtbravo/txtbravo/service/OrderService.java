package com.txtbravo.txtbravo.service;

import com.txtbravo.txtbravo.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService
{
    Order save(Order address);

    Page<Order> findAll(Pageable pageable);

    Order findById(Long id);

    void delete(Order Order);
}