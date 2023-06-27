package com.example.demo.service;

import com.example.demo.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    void addRecord(Long orderId, Long bookId, Long quantity, Long status);

    List<OrderItem> findByOrderId(Long orderId);
}
