package com.example.manage.api.controller.admin.common;

import com.example.manage.domain.ResultData;
import com.example.manage.enums.common.ResultCode;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.lang.reflect.Field;

/**
 * 公共controller
 * @author zzm
 * @date 2022/4/3 17:48
 */
public class BaseController {

    /**
     * JSR303 按字段顺序返回错误消息
     * @author zzm
     * @date 2022/4/3 17:47
     * @param bindResult
     * @return java.lang.String
     */
    protected String errorMessage(BindingResult bindResult) {
        Field[] fields = bindResult.getTarget().getClass().getDeclaredFields();
        for (Field field : fields) {
            for (FieldError error : bindResult.getFieldErrors()) {
                if (field.getName().equalsIgnoreCase(error.getField())) {
                    return error.getDefaultMessage();
                }
            }
        }
        return null;
    }

    /**
     * 成功
     */
    protected <T> ResultData<T> success(T data) {
        return success(ResultCode.SUCCESS.getMsg(), data);
    }

    /**
     * 成功
     */
    protected <T> ResultData<T> success() {
        return success(null);
    }

    /**
     * 成功
     */
    protected <T> ResultData<T> success(String message, T data) {
        return new ResultData<>(ResultCode.SUCCESS.getCode(), message, true, data);
    }

    /**
     * 失败
     */
    protected <T> ResultData<T> fail(String message) {
        return fail(ResultCode.FAIL.getCode(), message);
    }

    /**
     * 失败
     */
    protected <T> ResultData<T> fail(String code, String message) {
        return new ResultData<>(code, message, false, null);
    }

    /**
     * 默认更新
     */
    protected ResultData<String> updateDefault(Boolean result) {
        return result ? success(ResultCode.UPDATE_SUCCESS.getMsg(), "") : fail(ResultCode.UPDATE_FAIL.getMsg());
    }

    /**
     * 默认保存
     */
    protected ResultData<String> saveDefault(Boolean result) {
        return result ? success(ResultCode.SAVE_SUCCESS.getMsg(), "") : fail(ResultCode.SAVE_FAIL.getMsg());
    }

    /**
     * 默认删除
     */
    protected ResultData<String> deleteDefault(Boolean result) {
        return result ? success(ResultCode.DELETE_SUCCESS.getMsg(), "") : fail(ResultCode.DELETE_FAIL.getMsg());
    }
}
