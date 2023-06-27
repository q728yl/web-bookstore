package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/getList")
    public Map<String, Object> getList1(){
        List<Book> bookList = bookService.findAll();
        Map<String, Object> map = new HashMap<>();
        map.put("Books",bookList);
        return map;
    }
    @PostMapping("/addBook")
    public void addBook(@RequestBody Map<String,Object> body){
        String title = (String) body.get("title");
        String IBSN = (String) body.get("IBSN");
        String author = (String) body.get("author");
        String image = (String) body.get("image");
        String description = (String) body.get("description");
        BigDecimal price = new BigDecimal(body.get("price").toString());
        BigDecimal rating = new BigDecimal(body.get("rating").toString());
        Long quantity = Long.valueOf(body.get("quantity").toString());
        Boolean in_stock  = (Long.valueOf(body.get("in_stock").toString())) == 1;//(title,IBSN,author,image,description,price,rating,in_stock,quantity);
        Book book = new Book();
        book.setTitle(title);
        book.setIBSN(IBSN);
        book.setAuthor(author);
        book.setImage(image);
        book.setDescription(description);
        book.setPrice(price);
        book.setRating(rating);
        book.setQuantity(quantity);
        book.setIn_stock(in_stock);
        bookService.addBook(book);

    }
    @PostMapping("/delBook")
    public void delBook(@RequestBody Map<String,Object> body){
        Long bookId = Long.valueOf(body.get("bookId").toString());

        bookService.delBook(bookId);
    }
    @PostMapping("/updateBook")
    public void updateBook(@RequestBody Map<String,Object> body){
        System.out.println(body);
        Long bookId = Long.valueOf(body.get("bookId").toString());
        String title = (String) body.get("title");
        String IBSN = (String) body.get("IBSN");
        String author = (String) body.get("author");
        String image = (String) body.get("image");
        String description = (String) body.get("description");
        BigDecimal price = new BigDecimal(body.get("price").toString());
        BigDecimal rating = new BigDecimal(body.get("rating").toString());
        Long quantity = Long.valueOf(body.get("quantity").toString());
        Boolean in_stock  = (Long.valueOf(body.get("in_stock").toString())) == 1||((body.get("in_stock").toString()))=="true";//(title,IBSN,author,image,description,price,rating,in_stock,quantity);
        Book book = bookService.findBookById(bookId);
        book.setTitle(title);
        book.setIBSN(IBSN);
        book.setAuthor(author);
        book.setImage(image);
        book.setDescription(description);
        book.setPrice(price);
        book.setRating(rating);
        book.setQuantity(quantity);
        book.setIn_stock(in_stock);
        bookService.updateBook(bookId,book);


    }

}
