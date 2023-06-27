package com.example.demo.repository;

import com.example.demo.entity.UserAuth;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserAuthRepository extends JpaRepository<UserAuth,Long> {
//   @Query("SELECT u FROM UserAuth u where  and u.password = ?2")
//    UserAuth findUserAuthByUsernameAndPassword(String username,String password);
    UserAuth findByUserId(Long userId);
}
