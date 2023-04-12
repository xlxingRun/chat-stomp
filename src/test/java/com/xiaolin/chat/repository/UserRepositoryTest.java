package com.xiaolin.chat.repository;

import com.xiaolin.chat.model.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
    }

    @Test
    void assertUserNotExists() {
        User user = new User("Mike", "123456");
        userRepository.save(user);

        List<User> users = userRepository.findAll();
        assert !users.isEmpty();
    }

    @Test
    void assertFindByUserName() {
        User user = new User("Tom", "123456");
        userRepository.save(user);

        Optional<User> u = userRepository.findUserByUsername(user.getUsername());
        assert u.isPresent();
        assertEquals(u.get().getId(), user.getId());
    }
}