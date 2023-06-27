package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.model.Message;

import java.util.List;

public interface OrderService {
    Message placeOrder(Long userId);

    Long getOrderNum();

    List<Order> getOrderByUserId(Long userId);

    List<Order> getAllOrder();
}
