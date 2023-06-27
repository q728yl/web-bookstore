package com.example.demo.Dao;

import com.example.demo.entity.Order;

import java.util.List;

public interface OrderDao {
    void placeOrder(Long userId);

    Long getOrderNum();

    List<Order> getOrderByUserId(Long userId);

    List<Order> getAllOrder();
}
