package com.example.demo.service;

import com.example.demo.entity.Book;

import java.util.List;

public interface BookService {
    void addBook(Book book);
    List<Book> findAll();
    void delBook(Long bookId);
    Book findBookById(Long bookId);

    void setQuantity(Long bookId,Long quantity);

    void updateBook(Long bookId, Book book);
}
