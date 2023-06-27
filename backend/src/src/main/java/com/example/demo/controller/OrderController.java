package com.example.demo.controller;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.model.Message;
import com.example.demo.model.OrderDatas;
import com.example.demo.service.OrderItemService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {


    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;

    @PostMapping("/ordersById")
    public Message getOrdersByUserId(@RequestBody Map<String, Object> body) {
        Long userId = Long.valueOf(body.get("userId").toString());
        List<OrderItem> allOrderItems = new ArrayList<>();
        List<List<Integer>> date = new ArrayList<>();

        // Retrieve orders by user ID
        List<Order> orders = orderService.getOrderByUserId(userId);

        for (Order order : orders) {
            List<Integer> t = new ArrayList<>();
            t.clear();
            Long orderId = order.getId();
            List<OrderItem> orderItems = orderItemService.findByOrderId(orderId);
            allOrderItems.addAll(orderItems);


            t.add(order.getOrderDate().getYear());
            t.add(order.getOrderDate().getMonthValue());
            t.add(order.getOrderDate().getDayOfMonth());
            t.add(order.getOrderDate().getHour());
            t.add(order.getOrderDate().getMinute());
            t.add(order.getOrderDate().getSecond());
            date.add(t);

        }

        OrderDatas orderData = new OrderDatas(date, allOrderItems);
        return new Message("getOrder success", true, orderData);
    }

    @PostMapping("/allOrders")
    public Message getAllOrders(@RequestBody Map<String, Object> body) {
       // Long userId = Long.valueOf(body.get("userId").toString());
        List<OrderItem> allOrderItems = new ArrayList<>();
        List<List<Integer>> date = new ArrayList<>();

        List<Order> orders = orderService.getAllOrder();

        for (Order order : orders) {
            List<Integer> t = new ArrayList<>();
            t.clear();
            Long orderId = order.getId();
            List<OrderItem> orderItems = orderItemService.findByOrderId(orderId);
            allOrderItems.addAll(orderItems);


            t.add(order.getOrderDate().getYear());
            t.add(order.getOrderDate().getMonthValue());
            t.add(order.getOrderDate().getDayOfMonth());
            t.add(order.getOrderDate().getHour());
            t.add(order.getOrderDate().getMinute());
            t.add(order.getOrderDate().getSecond());
            date.add(t);

        }

        OrderDatas orderData = new OrderDatas(date, allOrderItems);
        return new Message("getOrder success", true, orderData);
    }

}