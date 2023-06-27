package com.example.demo.model;

import com.example.demo.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDatas {
    private List<List<Integer>> dates;
    private List<OrderItem> orderItems;



}
