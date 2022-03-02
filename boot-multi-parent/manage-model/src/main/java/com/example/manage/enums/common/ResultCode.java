package com.example.manage.enums.common;

import com.example.manage.domain.IResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode implements IResultCode {

    /**
     * 操作失败
     */
    FAIL("-1", "操作失败"),

    /**
     * 操作成功
     */
    SUCCESS("200", "操作成功"),

    /**
     * 更新成功
     */
    UPDATE_SUCCESS("200", "更新成功"),

    /**
     * 更新失败
     */
    UPDATE_FAIL("-1", "更新失败"),

    /**
     * 保存失败
     */
    SAVE_FAIL("-1", "保存失败"),

    /**
     * 保存成功
     */
    SAVE_SUCCESS("200", "保存成功"),

    /**
     * 保存成功
     */
    DELETE_SUCCESS("200", "删除成功"),

    /**
     * 保存成功
     */
    DELETE_FAIL("-1", "删除失败"),

    /**
     * 请求成功
     */
    PAGE("200", "请求成功"),


    /**
     * 请求成功
     */
    URL_ERROR("-1", "地址有误"),


    /**
     * 请求方式有误
     */
    POST_METHOD_ERROR("-1", "请求方式有误");

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
