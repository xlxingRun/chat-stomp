package com.xiaolin.chat.repository;

import com.xiaolin.chat.model.entity.ChatMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ChatMessageRepositoryTest {
    @Autowired
    private ChatMessageRepository chatMessageRepository;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {

    }

    /**
     * 根据id返回聊天记录
     */
    @Test
    void assertFindChatMessageById() {
        ChatMessage chatMessage = new ChatMessage().setContent("Hello, world!").setSender("Tom");
        chatMessageRepository.save(chatMessage);
        Optional<ChatMessage> cm = chatMessageRepository.findById(chatMessage.getId());

        // 根据用户id查询数据库条目
        assert cm.isPresent();
        assertEquals(cm.get().getId(), chatMessage.getId());

        // 测试接收者为null
        assertNull(cm.get().getReceiver());
        // 测试发送者为Tom
        assertEquals(cm.get().getSender(), "Tom");
    }

    /**
     * 插入和查询数据库错误
     */
    @Test
    void assertUserNotExists() {

    }
}
