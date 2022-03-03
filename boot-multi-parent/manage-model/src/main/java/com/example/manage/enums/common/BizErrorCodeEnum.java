package com.example.manage.enums.common;


import com.alibaba.druid.util.StringUtils;
import com.example.manage.domain.IResultCode;

/**
 * 业务错误码
 */
public enum BizErrorCodeEnum implements IResultCode {

    /**
     * 参数异常
     */
    PARAMETER_ERROR("10001", "参数异常"),

    /**
     * 分页参数异常
     */
    PAGE_PARAMETER_ERROR("10002", "分页参数异常"),

    /**
     * 系统异常
     */
    SYSTEM_ERROR("500", "系统异常"),

    /**
     * 访问次数受限制（限流异常）
     */
    LIMIT_ERROR("501", "访问次数受限制"),


    /**
     * 操作太频繁
     */
    OPERATE_ERROR("502", "操作太频繁");


    /**
     * 错误码
     */
    private final String code;

    /**
     * 描述
     */
    private final String msg;

    /**
     * @param code 错误码
     * @param msg  描述
     */
    BizErrorCodeEnum(final String code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据编码查询枚举。
     *
     * @param code 编码。
     * @return 枚举。
     */
    public static BizErrorCodeEnum getByCode(String code) {
        for (BizErrorCodeEnum value : BizErrorCodeEnum.values()) {
            if (StringUtils.equals(code, value.getCode())) {
                return value;
            }
        }
        return null;
    }

    /**
     * 枚举是否包含此code
     *
     * @param code 枚举code
     * @return 结果
     */
    public static Boolean contains(String code) {
        for (BizErrorCodeEnum value : BizErrorCodeEnum.values()) {
            if (StringUtils.equals(code, value.getCode())) {
                return true;
            }
        }
        return false;
    }

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
