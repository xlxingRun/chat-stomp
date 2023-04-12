package com.xiaolin.chat.config;

import com.xiaolin.chat.ChatApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ChatApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebSocketConfigTest {

    @BeforeEach
    void beforeEach() {
        System.out.println("beforeEach");
    }
    @Test
    void assertHello() {
        System.out.println("assertHello");
    }

    @AfterEach
    void afterEach() {
        System.out.println("afterEach");
    }

}