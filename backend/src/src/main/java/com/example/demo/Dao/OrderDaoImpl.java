package com.example.demo.Dao;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDaoImpl implements OrderDao{
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public void placeOrder(Long userId){
        orderRepository.placeOrder(userId);
    }
    @Override
    public Long getOrderNum(){
        return orderRepository.getOrderCount();
    }
    @Override
    public List<Order> getOrderByUserId(Long userId){
        return orderRepository.findByUseId(userId);
    }
    @Override
    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }
}
