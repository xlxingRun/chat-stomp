package com.xiaolin.chat.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 统一的http回复消息
 * @author xlxing
 */
@Accessors(chain = true)
@Getter
@Setter
public class Response<T> {
    private Integer code;
    private T data;
    private String message;

    private static final Integer SUCCESS = 0;
    private static final Integer FAILED = -1;

    public Response() {}

    public static <T> Response<T> ok() {
        return new Response<T>()
                .setCode(SUCCESS);
    }
    public static <T> Response<T> ok(T data) {
        return new Response<T>()
                .setCode(SUCCESS)
                .setData(data);
    }
    public static <T> Response<T> ok(String message) {
        return new Response<T>()
                .setCode(SUCCESS)
                .setMessage(message);
    }
    public static <T> Response<T> failed(String message) {
        return new Response<T>()
                .setCode(FAILED)
                .setMessage(message);
    }

}
