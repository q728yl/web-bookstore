package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findAll();
    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.in_stock  = 0 WHERE b.id = :bookId")
    void updateStock(  Long bookId);
    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.quantity  = :quantity WHERE b.id = :bookId")
    void setQuantity(@Param("bookId")Long bookId,@Param("quantity") Long quantity);
    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.title = :title, b.IBSN = :ibsn, b.author = :author, b.image = :image, " +
            "b.description = :description, b.price = :price, b.rating = :rating, b.in_stock = :inStock, " +
            "b.quantity = :quantity WHERE b.id = :bookId")
    void updateById(@Param("bookId") Long bookId, @Param("title") String title, @Param("ibsn") String ibsn,
                    @Param("author") String author, @Param("image") String image,
                    @Param("description") String description, @Param("price") BigDecimal price,
                    @Param("rating") BigDecimal rating, @Param("inStock") Boolean inStock,
                    @Param("quantity") Long quantity);


//    List<Book> findById(Long id);


}
