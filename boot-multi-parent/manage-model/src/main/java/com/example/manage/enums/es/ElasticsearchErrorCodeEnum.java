package com.example.manage.enums.es;


import com.alibaba.druid.util.StringUtils;
import com.example.manage.domain.IResultCode;

/**
 * 业务错误码
 */
public enum ElasticsearchErrorCodeEnum implements IResultCode {

    GET_INFO_ERROR("30001", "获取ES信息失败"),

    LOAD_DATA_ERROR("30002", "检索数据失败"),

    NOT_DATA_ERROR("30003", "无数据"),

    ADD_DATA_ERROR("30004", "向ES添加数据失败"),

    UPDATE_DATA_ERROR("30005", "修改ES中的数据失败"),

    DELETE_DATA_ERROR("30006", "删除ES中的数据失败"),

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
     * @param code 错误码
     * @param msg  描述
     */
    ElasticsearchErrorCodeEnum(final String code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据编码查询枚举。
     *
     * @param code 编码。
     * @return 枚举。
     */
    public static ElasticsearchErrorCodeEnum getByCode(String code) {
        for (ElasticsearchErrorCodeEnum value : ElasticsearchErrorCodeEnum.values()) {
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
        for (ElasticsearchErrorCodeEnum value : ElasticsearchErrorCodeEnum.values()) {
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
