package com.example.manage.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 自定义了验证码错误的异常，它继承了Spring Security的AuthenticationException：
 *
 * @author zzm
 * @date 2022/4/4 21:09
 */
public class CaptchaException extends AuthenticationException {

    public CaptchaException(String msg) {
        super(msg);
    }
}
