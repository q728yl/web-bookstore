package com.example.demo.Dao;

import com.example.demo.entity.OrderItem;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderItemDaoImpl implements OrderItemDao{
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Override
    public void addRecord(Long orderId, Long bookId, Long quantity, Long status){
        orderItemRepository.addRecord( orderId,  bookId,  quantity,  status);
    }
    @Override
    public List<OrderItem> findByOrderId(Long orderId){
        return orderItemRepository.getOrderItemByOrderId( orderId);
    }

}
