package com.example.manage.cache.impl;

import cn.hutool.core.util.ObjectUtil;
import com.example.manage.cache.ICacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.oschina.j2cache.CacheChannel;
import org.springframework.stereotype.Repository;

/**
 * j2cache 缓存服务
 *
 */
@Slf4j
@RequiredArgsConstructor
@Repository
public class CacheServiceImpl implements ICacheService {

    private final CacheChannel cacheChannel;

    /**
     * 缓存数据
     *
     * @param key   :
     * @param value :
     * @return
     * @author abs
     * @date 2020/10/28 16:09
     */
    @Override
    public Boolean cacheValue(String key, String value) {
        try {
            cacheChannel.set(KEY_PREFIX_VALUE, key, value);
            return true;
        } catch (Throwable t) {
            log.error("缓存[" + KEY_PREFIX_VALUE + key + "]失败, value[" + value + "]", t);
        }
        return false;
    }

    /**
     * 获取缓存
     *
     * @param key :
     * @return
     * @author abs
     * @date 2020/10/28 16:13
     */
    @Override
    public String getValue(String key) {
        try {
            Object value = cacheChannel.get(KEY_PREFIX_VALUE, key).getValue();
            return ObjectUtil.isNull(value) ? null : value.toString();
        } catch (Throwable t) {
            log.error("获取缓存失败key[" + KEY_PREFIX_VALUE + key + ", Codeor[" + t + "]");
        }
        return null;
    }

    /**
     * 删除缓存
     *
     * @param key :
     * @return
     * @author abs
     * @date 2020/10/28 16:09
     */
    @Override
    public Boolean removeValue(String key) {
        try {
            cacheChannel.evict(KEY_PREFIX_VALUE, key);
            return true;
        } catch (Throwable t) {
            log.error("移除缓存失败key[" + KEY_PREFIX_VALUE + key + ", Codeor[" + t + "]");
        }
        return false;
    }


}
