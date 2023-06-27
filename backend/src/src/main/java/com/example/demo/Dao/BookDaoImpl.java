package com.example.demo.Dao;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDaoImpl implements BookDao{
    @Autowired
    private BookRepository bookRepository;
    @Override
    public void addBook(Book book){
        bookRepository.save(book);
    }
    @Override
    public void delBook(Long bookId){
        bookRepository.updateStock(bookId);
    }
    @Override
    public List<Book> findAll(){
        return bookRepository.findAll();
    }
    @Override
    public Book findBookById(Long bookId){
        return bookRepository.getById(bookId);
    }
    @Override
    public void setQuantity(Long bookId, Long quantity){
        bookRepository.setQuantity(bookId,quantity);;

    }
    @Override
    public void updateBook(Long bookId, Book book){
        bookRepository.updateById(bookId,book.getTitle(),book.getIBSN(),book.getAuthor(),book.getImage(),book.getDescription(),book.getPrice(),book.getRating(),book.getIn_stock(),book.getQuantity());
    }
}
