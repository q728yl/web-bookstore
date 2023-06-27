package com.example.demo.repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUseId(Long userId);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO orders (user_id) VALUES (:userId)", nativeQuery = true)
    void placeOrder(@Param("userId") Long userId);

    @Query(value = "SELECT COUNT(*) FROM orders", nativeQuery = true)
    Long getOrderCount();

}
