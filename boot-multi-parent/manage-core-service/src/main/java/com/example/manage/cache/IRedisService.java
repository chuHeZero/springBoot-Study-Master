package com.example.manage.cache;

import org.springframework.data.redis.core.ListOperations;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis缓存service
 *
 * @author zzm
 * @date 2022/4/3 18:14
 */
public interface IRedisService {

    /**
     * 添加
     *
     * @param key
     * @param value
     * @param time
     * @return
     */
    boolean cacheValue(String key, String value, long time);

    /**
     * 添加
     *
     * @param key
     * @param value
     * @return
     */
    boolean cacheValue(String key, String value);

    /**
     * 是否包含
     *
     * @param key
     * @return
     */
    boolean containsValueKey(String key);

    /**
     * 是否包含
     *
     * @param key
     * @return
     */
    boolean containsSetKey(String key);

    /**
     * 是否包含
     *
     * @param key
     * @return
     */
    boolean containsListKey(String key);

    /**
     * 是否包含
     *
     * @param key
     * @return
     */
    boolean containsKey(String key);

    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    String getValue(String key);

    /**
     * 移除缓存
     *
     * @param key
     * @return
     */
    boolean removeValue(String key);

    /**
     * 移除缓存
     *
     * @param key
     * @return
     */
    boolean removeSet(String key);

    /**
     * 移除缓存
     *
     * @param key
     * @return
     */
    boolean removeList(String key);

    /**
     * 缓存Set
     *
     * @param key
     * @param value
     * @param time
     * @return
     */
    boolean cacheSet(String key, String value, long time);

    /**
     * 缓存Set
     *
     * @param key
     * @param value
     * @return
     */
    boolean cacheSet(String key, String value);

    /**
     * 缓存Set
     *
     * @param k
     * @param v
     * @param time
     * @return
     */
    boolean cacheSet(String k, Set<String> v, long time);

    /**
     * 缓存Set
     *
     * @param k
     * @param v
     * @return
     */
    boolean cacheSet(String k, Set<String> v);

    /**
     * 获取Set
     *
     * @param k
     * @return
     */
    Set<String> getSet(String k);

    /**
     * 缓存List
     *
     * @param k
     * @param v
     * @param time
     * @return
     */
    boolean cacheList(String k, String v, long time);

    /**
     * 缓存List
     *
     * @param k
     * @param v
     * @return
     */
    boolean cacheList(String k, String v);

    /**
     * 缓存List
     *
     * @param k
     * @param v
     * @param time
     * @return
     */
    boolean cacheList(String k, List<String> v, long time);

    /**
     * 缓存List
     *
     * @param k
     * @param v
     * @return
     */
    boolean cacheList(String k, List<String> v);

    /**
     * 获取List
     *
     * @param k
     * @param start
     * @param end
     * @return
     */
    List<String> getList(String k, long start, long end);

    /**
     * 获取页码
     *
     * @param key
     * @return
     */
    long getListSize(String key);

    /**
     * 获取页码
     *
     * @param listOps
     * @param k
     * @return
     */
    long getListSize(ListOperations<String, String> listOps, String k);

    /**
     * 移除list缓存
     *
     * @param k
     * @return
     */
    boolean removeOneOfList(String k);

    /**
     * 前缀移除缓存
     *
     * @param prefix
     * @return
     */
    boolean removeByPrefix(String prefix);

    /**
     * 功能描述: 递增
     *
     * @param:
     * @return:
     * @auther: wuqi
     * @date: 2019/12/19 17:10
     */
    long incr(String key, long delta);

    /**
     * 功能描述: 递减
     *
     * @param:
     * @return:
     * @auther: wuqi
     * @date: 2019/12/19 17:10
     */
    long decr(String key, long delta);

    /**
     * 功能描述: 设置过期时间
     *
     * @param:
     * @return:
     * @auther: wuqi
     * @date: 2019/12/19 19:00
     */
    boolean expire(String key, long time);

    /**
     * 功能描述: 查看过期时间
     *
     * @param:
     * @return:
     * @auther: wuqi
     * @date: 2019/12/19 19:00
     */
    long getExpire(String key);

    /**
     * 查询匹配的key
     *
     * @param pattern:
     * @return
     * @author zzm
     * @date 2020/8/10 11:22
     */
    List<String> scan(String pattern);

    /**
     * @auther: anchun
     * @date: 2020/8/19 19:00
     */
    <T> T getObject(String key);

    /**
     * @auther: anchun
     * @date: 2020/8/19 19:00
     */
    void cacheObject(String key, Object value, long duration, TimeUnit unit);
}
