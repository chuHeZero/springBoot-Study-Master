package com.example.manage.cache;

/**
 * j2cache 缓存服务
 */
public interface ICacheService {

    /**
     * value前缀
     */
    String KEY_PREFIX_VALUE = "j2cache:manage:value";

    /**
     * 缓存数据
     * @param key:
     * @param value:
     * @return
     * @author abs
     * @date 2020/10/28 16:09
     */
    Boolean cacheValue(String key, String value);

    /**
     * 获取缓存
     * @param key:
     * @return
     * @author abs
     * @date 2020/10/28 16:13
     */
    String getValue(String key);

    /**
     * 删除缓存
     * @param key:
     * @return
     * @author abs
     * @date 2020/10/28 16:09
     */
    Boolean removeValue(String key);
}
