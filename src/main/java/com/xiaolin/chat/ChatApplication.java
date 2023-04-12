package com.xiaolin.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO: 待完善，需要增加一些功能
 * TODO: 每次登陆可以显示最近10条聊天记录
 * TODO: 完善WebSocket的鉴权
 * TODO: 对鉴权流程有更深的了解
 * @author xlxing
 */

@SpringBootApplication
public class ChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);
    }
}
