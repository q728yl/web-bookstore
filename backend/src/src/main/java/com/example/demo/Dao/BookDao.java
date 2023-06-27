package com.example.demo.Dao;

import com.example.demo.entity.Book;

import java.util.List;

public interface BookDao {
    void addBook(Book book);

    void delBook(Long bookId);

    List<Book> findAll();

    Book findBookById(Long bookId);

    void setQuantity(Long bookId,Long quantity);

    void updateBook(Long bookId, Book book);
}
