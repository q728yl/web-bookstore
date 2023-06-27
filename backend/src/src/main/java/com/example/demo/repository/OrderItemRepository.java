package com.example.demo.repository;

import com.example.demo.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {

    List<OrderItem> getOrderItemByOrderId(Long orderId);

//    List<OrderItem> findByU(Long userId, Long bookId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO orderitem (order_id, book_details_id, quantity, status) " +
            "VALUES (:orderId, :bookId, :quantity, :status)", nativeQuery = true)
    void addRecord(
            @Param("orderId") Long orderId,
            @Param("bookId") Long bookId,
            @Param("quantity") Long quantity,
            @Param("status") Long status
    );



}
