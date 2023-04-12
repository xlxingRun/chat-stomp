package com.xiaolin.chat.service;

import com.xiaolin.chat.model.entity.User;
import com.xiaolin.chat.model.Response;

/**
 * @author xlxing
 */
public interface LoginService {
    /**
     * 用户存在
     *  - 校验成功 -> 登陆成功
     *  - 校验失败 -> 登陆失败
     * 用户不存在 -> 注册并登陆成功
     * @param user 用户登陆DTO，必须包含账户名和密码
     * @return 登陆消息
     */
    Response<?> loginOrRegister(User user);
}
