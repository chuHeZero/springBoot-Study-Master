package com.example.manage.cache;

/**
 * j2cache 缓存服务
 * @author zzm
 * @date 2022/4/3 18:14
 */
public interface ICacheService {

    /**
     * value前缀
     */
    String KEY_PREFIX_VALUE = "j2cache:manage:value";

    /**
     * @Description: 缓存数据
     * @Param: [key, value]
     * @Return: java.lang.Boolean
     * @Author: XiaoHuoLong
     * @Date: 2022/3/16 18:04
     */
    Boolean cacheValue(String key, String value);

    /**
     * @Description: 获取缓存
     * @Param: [key]
     * @Return: java.lang.String
     * @Author: XiaoHuoLong
     * @Date: 2022/3/16 18:04
     */
    String getValue(String key);

    /**
     * @Description: 删除缓存
     * @Param: [key]
     * @Return: java.lang.Boolean
     * @Author: XiaoHuoLong
     * @Date: 2022/3/16 18:04
     */
    Boolean removeValue(String key);
}
