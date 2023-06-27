package com.example.demo.service;

import com.example.demo.Dao.BookDao;
import com.example.demo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookDao bookDao;
    @Override
    public void addBook(Book book){
        bookDao.addBook(book);
    }
    @Override
    public void delBook(Long bookId){
        bookDao.delBook(bookId);
    }
    @Override
    public List<Book> findAll(){
        return bookDao.findAll();
    }
    @Override
    public Book findBookById(Long bookId){
        return bookDao.findBookById(bookId);
    }
    @Override
    public void setQuantity(Long bookId,Long quantity){
        bookDao.setQuantity(bookId,quantity);
    }
    @Override
    public    void updateBook(Long bookId, Book book){
        bookDao.updateBook( bookId, book);
    }
}
