package com.example.weather_service.schedulers;

import com.example.weather_service.Entity.Order;
import com.example.weather_service.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderScheduler {
    @Autowired
    OrderRepository orderRepository;

    public void processPendingOrders(){
        List<Order> orderList = orderRepository.findByStatus("Pending");
        orderList.forEach(order -> {
            order.setStatus("Completed");
            orderRepository.save(order);
        });
        System.out.println("Processed "+orderList.size()+" orders");
    }
}
