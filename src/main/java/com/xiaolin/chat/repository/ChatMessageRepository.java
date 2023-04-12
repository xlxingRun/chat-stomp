package com.xiaolin.chat.repository;

import com.xiaolin.chat.model.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author xlxing
 */
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    /**
     * 根据用户名获取聊天记录
     * @param sender: 消息发送者
     * @param receiver: 消息接收者
     * @return 多条信息的列表
     */
    List<ChatMessage> findChatMessagesBySenderOrReceiver(String sender, String receiver);


}
