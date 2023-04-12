package com.xiaolin.chat.controller;

import com.xiaolin.chat.model.entity.User;
import com.xiaolin.chat.model.Response;
import com.xiaolin.chat.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 用户登陆相关
 * @author xlxing
 */

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final LoginService loginService;

    @PostMapping("/users")
    public Response<?> loginOrRegister(@RequestParam String username, @RequestParam String password) {
        return loginService.loginOrRegister(new User().setUsername(username).setPassword(password));
    }
}
