package com.example.manage.enums.common;

import com.example.manage.domain.IResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthErrorCodeEnum implements IResultCode {

    AUTH_STATUS_ERROR("20001", "当前登录状态过期"),

    AUTH_NO_ERROR("20002", "找不到当前登录的信息"),

    USER_CREDENTIALS_ERROR("20003", "密码错误"),

    USER_CREDENTIALS_EXPIRED("20004", "密码过期"),

    USER_ACCOUNT_DISABLE("20005", "账号不可用"),

    USER_ACCOUNT_LOCKED("20006", "账号被锁定"),

    USER_ACCOUNT_NOT_EXIST("20007", "账号不存在"),

    USER_ACCOUNT_ALREADY_EXIST("20008", "账号已存在"),

    USER_ACCOUNT_USE_BY_OTHERS("20009", "账号下线"),

    USER_NOT_LOGIN("20010", "用户未登录"),

    USER_ACCOUNT_EXPIRED("20011", "账号已过期"),

    VERIFICATION_CODE_ERROR("20012", "验证码错误"),
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
