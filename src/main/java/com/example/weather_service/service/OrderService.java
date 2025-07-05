package com.example.weather_service.service;

import com.example.weather_service.Entity.Order;
import com.example.weather_service.Repository.OrderRepository;
import com.example.weather_service.dto.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @PostMapping
    public Order createOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setCustomerEmail(orderRequest.getCustomerEmail());
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
