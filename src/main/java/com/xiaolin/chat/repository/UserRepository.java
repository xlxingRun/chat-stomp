package com.xiaolin.chat.repository;

import com.xiaolin.chat.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * 用户数据库操作
 * @author xlxing
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 指定的用户
     */
    Optional<User> findUserByUsername(String username);
}
