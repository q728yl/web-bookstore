package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

//public interface UserRepository {
//    List<User> findAllUsers();
//    List<User> findUserById(Long id);
//}
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.id = ?1")
    List<User> findUserById(Long id);
    @Query("SELECT u FROM User u WHERE u.user_type = ?1")
    List<User> findUserByUser_type(Long user_type);

    List<User> findAll();
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.comments = :comments WHERE u.id = :userId")
    void saveReviewComment(Long userId, String comments);

    User findUserByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.status  = 0 WHERE u.id = :userId")
    void banUsers(Long userId);
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.status  = 1 WHERE u.id = :userId")
    void noBanUsers(Long userId);
}
