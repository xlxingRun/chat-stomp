package com.xiaolin.chat.service.impl;

import com.xiaolin.chat.model.entity.User;
import com.xiaolin.chat.model.Response;
import com.xiaolin.chat.repository.UserRepository;
import com.xiaolin.chat.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户登陆相关的业务
 * @author xlxing
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    @Override
    public Response<?> loginOrRegister(User user) {
        var userOptional = userRepository.findUserByUsername(user.getUsername());
        if (userOptional.isPresent()) {
            var u = userOptional.get();
            if (u.getPassword().equals(user.getPassword())) {
                return Response.ok("成功登陆");
            } else {
                return Response.failed("登陆失败，账户/密码错误");
            }
        } else {
            // 创建并登陆
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            return Response.ok("创建用户并登陆");
        }
    }
}
