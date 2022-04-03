package com.example.manage.constant;

/**
 * redis配置
 *
 * @author zzm
 * @date 2020/6/19 14:03
 */
public class RedisConstant {

    /**
     * value前缀
     */
    public static final String KEY_PREFIX_VALUE = "manage:value:";

    /**
     * set前缀
     */
    public static final String KEY_PREFIX_SET = "manage:set:";

    /**
     * list前缀
     */
    public static final String KEY_PREFIX_LIST = "manage:list:";

    /**
     * 配置key
     */
    public static final String CONFIG_KEY = "config";

    /**
     * token key
     */
    public static final String TOKEN_KEY = "token:";

    /**
     * 通知 key
     */
    public static final String NOTICE_KEY = "topic:notice";

    /**
     * 分
     */
    public static final Long MINUTE_EXPIRE = 60L;

    /**
     * 小时
     */
    public static final Long HOUR_EXPIRE = 60L * 60;

    /**
     * 90 分钟
     */
    public static final Long NINETY_MINUTE_EXPIRE = 60L * 90;

    /**
     * 天
     */
    public static final Long DAY_EXPIRE = 60L * 60 * 24;
}
