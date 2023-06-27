package com.example.demo.service;

import com.example.demo.Dao.OrderDao;
import com.example.demo.entity.Order;
import com.example.demo.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderDao orderDao;
    @Override
    public Message placeOrder(Long userId){
       orderDao.placeOrder(userId);
       return new Message("place succeed",true,null);
    }
    @Override
    public Long getOrderNum(){
        return orderDao.getOrderNum();
    }
    @Override
    public List<Order> getOrderByUserId(Long userId){
        return orderDao.getOrderByUserId(userId);
    }
    @Override
    public List<Order> getAllOrder(){
        return orderDao.getAllOrder();
    }
}
