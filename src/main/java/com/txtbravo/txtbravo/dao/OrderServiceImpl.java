package com.txtbravo.txtbravo.dao;


import com.txtbravo.txtbravo.entity.Order;
import com.txtbravo.txtbravo.repository.OrderRepository;
import com.txtbravo.txtbravo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order save(Order order)
    {
        return orderRepository.save(order);
    }

    @Override
    public Page<Order> findAll(Pageable pageable)
    {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order findById(Long id)
    {
        return orderRepository.findById(id);
    }

    @Override
    public void delete(Order order)
    {
        orderRepository.delete(order);
    }
}