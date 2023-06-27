package com.example.demo.Dao;

import com.example.demo.entity.OrderItem;

import java.util.List;

public interface OrderItemDao {
    void addRecord(Long orderId, Long bookId, Long quantity, Long status);

    List<OrderItem> findByOrderId(Long orderId);
}
