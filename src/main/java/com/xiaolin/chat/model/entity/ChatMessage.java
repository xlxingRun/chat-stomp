package com.xiaolin.chat.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author xlxing
 */
@Entity
@Accessors(chain = true)
@Data
public class ChatMessage {
    @Id @GeneratedValue
    private Long id;
    private String sender;
    private String receiver;
    private String content;
    private MessageType type;

    public ChatMessage() {}
    public ChatMessage(String sender, String receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }
    public enum MessageType {
        /**
         * 聊天
         */
        CHAT,
        /**
         * 上线
         */
        JOIN,
        /**
         * 离开
         */
        LEAVE
    }
}
