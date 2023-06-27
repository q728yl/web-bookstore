package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.entity.Cart;
import com.example.demo.model.Message;
import com.example.demo.service.BookService;
import com.example.demo.service.CartService;
import com.example.demo.service.OrderItemService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class PurchaseController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @Autowired
    private BookService bookService;
    @Autowired
    private OrderItemService orderItemService;

    @PostMapping("/purchase")
    public Message placeOrder(@RequestBody Map<String,Object> body) {
        Long userId = Long.valueOf(body.get("userId").toString());
        Message message = orderService.placeOrder(userId);
//        System.out.println(message.getMsg());
        List<Cart> carts = cartService.findByUserId(userId);
        Long orderId = orderService.getOrderNum();
        for (Cart cart :carts){
            System.out.println(cart);
            Long bookId =cart.getBookDetailsId();
            Long quantity = cart.getQuantity();
            Book book = bookService.findBookById(bookId);
            if (book.getQuantity() < quantity) {
                String errorMessage = book.getTitle() + "库存不足，下单失败";
                return new Message(errorMessage, false, null);
            }
            Long newQuantity = book.getQuantity()-quantity;
            System.out.println(newQuantity);
            bookService.setQuantity(bookId,newQuantity);

            Long status = 0L;
            orderItemService.addRecord(orderId,bookId,quantity,status);
        }

        cartService.deleteAll();
        return new Message("购买成功",true,null);

    }
    @PostMapping("/setQ")
    public void setQ(@RequestBody Map<String,Object> body) {
        Long bookId = Long.valueOf(body.get("bookId").toString());
        Long quantity = Long.valueOf(body.get("quantity").toString());
        bookService.setQuantity(bookId,quantity);
    }
}

