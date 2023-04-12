package com.xiaolin.chat.controller;

import com.xiaolin.chat.model.entity.ChatMessage;
import com.xiaolin.chat.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


/**
 * @author xlxing
 */
@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageRepository chatMessageRepository;
    /**
     * 接收来自客户端发送的信息
     * 转发到消息到 '/topic/public' ，所有订阅了 '/topic/public' 的客户均可以接收到广播消息
     * 转发到指定的receiver
     */
    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        chatMessageRepository.save(chatMessage);

        if (chatMessage.getReceiver() == null) {
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        } else {
            // 发送方和接收方都要收到消息回显
            messagingTemplate.convertAndSendToUser(chatMessage.getSender(), "notification", chatMessage);
            messagingTemplate.convertAndSendToUser(chatMessage.getReceiver(), "notification", chatMessage);
        }
    }

    /**
     * 获取增加用户信息
     * 建立Session头信息
     * 将消息广播出去
     * @param chatMessage 增加用户消息体
     * @param headerAccessor 头部获取器
     */
    @MessageMapping("/chat.addUser")
    public void addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        if (headerAccessor.getSessionAttributes() != null && chatMessage.getSender() != null) {
            headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }

}
