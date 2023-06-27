package com.example.demo.service;

import com.example.demo.Dao.OrderDao;
import com.example.demo.Dao.OrderItemDao;
import com.example.demo.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService{
    @Autowired
    private OrderItemDao orderItemDao;
    @Override
    public void addRecord(Long orderId, Long bookId, Long quantity, Long status){
        orderItemDao.addRecord(orderId,  bookId,  quantity,  status);
    }
    @Override
    public List<OrderItem> findByOrderId(Long orderId){
        return orderItemDao.findByOrderId(orderId);
    }
}
