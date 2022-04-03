package com.example.manage.enums.common;

import com.example.manage.domain.IResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthErrorCodeEnum implements IResultCode {

    /**
     * 当前登录状态过期
     */
    AUTH_STATUS_ERROR("20001", "当前登录状态过期"),


    /**
     * 找不到当前登录的信息
     */
    AUTH_NO_ERROR("20002", "找不到当前登录的信息"),

    ;

    /**
     * 错误码
     */
    private final String code;

    /**
     * 描述
     */
    private final String msg;

    /**
     * 获取错误码
     *
     * @return
     */
    @Override
    public String getCode() {
        return code;
    }

    /**
     * 获取错误信息
     *
     * @return
     */
    @Override
    public String getMsg() {
        return msg;
    }
}
