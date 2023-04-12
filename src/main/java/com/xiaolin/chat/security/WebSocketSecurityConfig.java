package com.xiaolin.chat.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.socket.EnableWebSocketSecurity;
import org.springframework.security.messaging.access.intercept.MessageMatcherDelegatingAuthorizationManager;

/**
 * TODO: 无法有效鉴权，需要排查原因，需要进一步学习Spring Security
 * 保护WebSocket连接，所有连接都需要校验
 * @author xlxing
 */

//@Configuration
//@EnableWebSocketSecurity
//public class WebSocketSecurityConfig {
//
//    @Bean
//    AuthorizationManager<Message<?>> authorizationManager(MessageMatcherDelegatingAuthorizationManager.Builder messages) {
//        messages.simpMessageDestMatchers("/**")
//                .authenticated()
//                .anyMessage().authenticated();
//        return messages.build();
//    }
//}
