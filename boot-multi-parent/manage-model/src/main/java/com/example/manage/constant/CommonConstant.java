package com.example.manage.constant;

import org.springframework.stereotype.Component;

/**
 * common
 *
 * @author zzm
 * @date 2020.01.09 11:09
 */
@Component
public class CommonConstant {

    /**
     * 创建人
     */
    public static final String DEFAULT_FIELD_CREATE_BY = "createBy";

    /**
     * 创建时间
     */
    public static final String DEFAULT_FIELD_CREATE_TIME = "createTime";

    /**
     * 修改人
     */
    public static final String DEFAULT_FIELD_UPDATE_BY = "updateBy";

    /**
     * 修改时间
     */
    public static final String DEFAULT_FIELD_UPDATE_TIME = "updateTime";

    /**
     * 修改时间
     */
    public static final String DEFAULT_FIELD_MODIFY_TIME = "modifyTime";

    /**
     * 创建用户ID
     */
    public static final String DEFAULT_FIELD_CREATE_USER_ID = "createUserId";

    /**
     * 修改用户ID
     */
    public static final String DEFAULT_FIELD_MODIFY_USER_ID = "modifyUserId";

    /**
     * 默认分组
     */
    public static final String CONFIG_DEFAULT_GP = "manage_default_gp";

    /**
     * 开始时间
     */
    public static final String DAY_BEGIN = " 00:00:00";

    /**
     * 结束时间
     */
    public static final String DAY_END = " 23:59:59";

    /**
     * 管理员权限
     */
    public static final String ADMIN_AUTH = "-1";

    /**
     * 脱敏权限
     */
    public static final String SENSITIVE_AUTH = "-2";

    /**
     * 销售权限
     */
    public static final String MARKET_AUTH = "-3";

    /**
     * 销售列表URL
     */
    public static final String MARKET_SENSITIVE_AUTH_URL = "";

    /**
     * 特殊字段排除 请使用 @IgnoreFieldSensitive(value = {"placeProvince", "placeCity", "placeRegion"})
     */
    @Deprecated
    public static final String MARKET_SENSITIVE_AUTH_PARAM = "placeProvince,placeCity,placeRegion,currentTimeId,currentTimeId,number";

    public static final String INDEX = "book";
}
