package com.example.demo.service;

import com.example.demo.entity.Cart;
import com.example.demo.model.Message;

import java.util.List;

public interface CartService {
    Message getCartById(Long userId);

    Message updateCart(Long userId, Long bookId);

    List<Cart> findByUserIdAndBookDetailsId(Long userId, Long bookId);

    void AddQuantity(Long id);

    void ReduceQuantity(Long id);

    void deleteCart(Long userId, Long bookId);

    List<Cart> findByUserId(Long userId);

    void deleteAll();
}
