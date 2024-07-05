package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 通用返回结果类，服务端响应的数据最终都会封装成此对象
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private T data; //数据

    public static <T> Result<T> success1(T object, String msg) {
        Result<T> r = new Result<T>();
        r.msg = msg;
        r.code = 200;
        r.data = object;
        return r;
    }

    public static <T> Result<T> success2(String msg) {
        Result<T> r = new Result<T>();
        r.msg = msg;
        r.code = 200;
        return r;
    }

    public static <T> Result<T> error(String msg) {
        Result r = new Result();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public Result<T> add(String key, Object value) {
        return this;
    }


}
