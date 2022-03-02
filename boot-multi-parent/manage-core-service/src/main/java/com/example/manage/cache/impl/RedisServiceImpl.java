package com.example.manage.cache.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.example.manage.cache.IRedisService;
import com.example.manage.constant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.SerializationUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Repository
public class RedisServiceImpl implements IRedisService {

    /**
     * template
     */
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisServiceImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 缓存value操作
     *
     * @param k    key
     * @param v    value
     * @param time time
     * @return boolean
     */
    @Override
    public boolean cacheValue(String k, String v, long time) {
        String key = RedisConstant.KEY_PREFIX_VALUE + k;
        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            valueOps.set(key, v);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            log.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存value操作
     *
     * @param k key
     * @param v value
     * @return boolean
     */
    @Override
    public boolean cacheValue(String k, String v) {
        return cacheValue(k, v, -1);
    }

    /**
     * 判断缓存是否存在
     *
     * @param k key
     * @return boolean
     */
    @Override
    public boolean containsValueKey(String k) {
        return containsKey(RedisConstant.KEY_PREFIX_VALUE + k);
    }

    /**
     * 判断缓存是否存在
     *
     * @param k key
     * @return boolean
     */
    @Override
    public boolean containsSetKey(String k) {
        return containsKey(RedisConstant.KEY_PREFIX_SET + k);
    }

    /**
     * 判断缓存是否存在
     *
     * @param k key
     * @return boolean
     */
    @Override
    public boolean containsListKey(String k) {
        return containsKey(RedisConstant.KEY_PREFIX_LIST + k);
    }

    @Override
    public boolean containsKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Throwable t) {
            log.error("判断缓存存在失败key[" + key + ", Codeor[" + t + "]");
        }
        return false;
    }

    /**
     * 获取缓存
     *
     * @param k key
     * @return string
     */
    @Override
    public String getValue(String k) {
        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            return valueOps.get(RedisConstant.KEY_PREFIX_VALUE + k);
        } catch (Throwable t) {
            log.error("获取缓存失败key[" + RedisConstant.KEY_PREFIX_VALUE + k + ", Codeor[" + t + "]");
        }
        return null;
    }

    /**
     * 移除缓存
     *
     * @param k key
     * @return boolean
     */
    @Override
    public boolean removeValue(String k) {
        return remove(RedisConstant.KEY_PREFIX_VALUE + k);
    }

    @Override
    public boolean removeSet(String k) {
        return remove(RedisConstant.KEY_PREFIX_SET + k);
    }

    @Override
    public boolean removeList(String k) {
        return remove(RedisConstant.KEY_PREFIX_LIST + k);
    }


    /**
     * 缓存set操作
     *
     * @param k    key
     * @param v    value
     * @param time time
     * @return boolean
     */
    @Override
    public boolean cacheSet(String k, String v, long time) {
        String key = RedisConstant.KEY_PREFIX_SET + k;
        try {
            SetOperations<String, String> valueOps = redisTemplate.opsForSet();
            valueOps.add(key, v);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            log.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存set
     *
     * @param k key
     * @param v value
     * @return boolean
     */
    @Override
    public boolean cacheSet(String k, String v) {
        return cacheSet(k, v, -1);
    }

    /**
     * 缓存set
     *
     * @param k    key
     * @param v    value
     * @param time time
     * @return boolean
     */
    @Override
    public boolean cacheSet(String k, Set<String> v, long time) {
        String key = RedisConstant.KEY_PREFIX_SET + k;
        try {
            SetOperations<String, String> setOps = redisTemplate.opsForSet();
            setOps.add(key, v.toArray(new String[v.size()]));
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            log.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存set
     *
     * @param k key
     * @param v value
     * @return boolean
     */
    @Override
    public boolean cacheSet(String k, Set<String> v) {
        return cacheSet(k, v, -1);
    }

    /**
     * 获取缓存set数据
     *
     * @param k key
     * @return set
     */
    @Override
    public Set<String> getSet(String k) {
        try {
            SetOperations<String, String> setOps = redisTemplate.opsForSet();
            return setOps.members(RedisConstant.KEY_PREFIX_SET + k);
        } catch (Throwable t) {
            log.error("获取set缓存失败key[" + RedisConstant.KEY_PREFIX_SET + k + ", Codeor[" + t + "]");
        }
        return null;
    }

    /**
     * list缓存
     *
     * @param k    key
     * @param v    value
     * @param time time
     * @return boolean
     */
    @Override
    public boolean cacheList(String k, String v, long time) {
        String key = RedisConstant.KEY_PREFIX_LIST + k;
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            listOps.rightPush(key, v);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            log.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存list
     *
     * @param k key
     * @param v value
     * @return boolean
     */
    @Override
    public boolean cacheList(String k, String v) {
        return cacheList(k, v, -1);
    }

    /**
     * 缓存list
     *
     * @param k    key
     * @param v    value
     * @param time time
     * @return boolean
     */
    @Override
    public boolean cacheList(String k, List<String> v, long time) {
        String key = RedisConstant.KEY_PREFIX_LIST + k;
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            listOps.rightPushAll(key, v);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            log.error("缓存[" + key + "]失败, value[" + v + "]", t);
        }
        return false;
    }

    /**
     * 缓存list
     *
     * @param k key
     * @param v value
     * @return boolean
     */
    @Override
    public boolean cacheList(String k, List<String> v) {
        return cacheList(k, v, -1);
    }

    /**
     * 获取list缓存
     *
     * @param k     key
     * @param start start
     * @param end   end
     * @return list
     */
    @Override
    public List<String> getList(String k, long start, long end) {
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            return listOps.range(RedisConstant.KEY_PREFIX_LIST + k, start, end);
        } catch (Throwable t) {
            log.error("获取list缓存失败key[" + RedisConstant.KEY_PREFIX_LIST + k + ", Codeor[" + t + "]");
        }
        return null;
    }

    /**
     * 获取总条数, 可用于分页
     *
     * @param k key
     * @return long
     */
    @Override
    public long getListSize(String k) {
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            return listOps.size(RedisConstant.KEY_PREFIX_LIST + k);
        } catch (Throwable t) {
            log.error("获取list长度失败key[" + RedisConstant.KEY_PREFIX_LIST + k + "], Codeor[" + t + "]");
        }
        return 0;
    }

    /**
     * 获取总条数, 可用于分页
     *
     * @param listOps listOps
     * @param k       k
     * @return long
     */
    @Override
    public long getListSize(ListOperations<String, String> listOps, String k) {
        try {
            return listOps.size(RedisConstant.KEY_PREFIX_LIST + k);
        } catch (Throwable t) {
            log.error("获取list长度失败key[" + RedisConstant.KEY_PREFIX_LIST + k + "], Codeor[" + t + "]");
        }
        return 0;
    }

    /**
     * 移除list缓存
     *
     * @param k k
     * @return boolean
     */
    @Override
    public boolean removeOneOfList(String k) {
        String key = RedisConstant.KEY_PREFIX_LIST + k;
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            listOps.rightPop(key);
            return true;
        } catch (Throwable t) {
            log.error("移除list缓存失败key[" + RedisConstant.KEY_PREFIX_LIST + k + ", Codeor[" + t + "]");
        }
        return false;
    }

    /**
     * 移除缓存
     *
     * @param key key
     * @return boolean
     */
    public boolean remove(String key) {
        try {
            redisTemplate.delete(key);
            return true;
        } catch (Throwable t) {
            log.error("移除缓存失败key[" + key + ", Codeor[" + t + "]");
        }
        return false;
    }

    /**
     * 根据前缀移除缓存
     *
     * @param prefix key
     * @return boolean
     */
    @Override
    public boolean removeByPrefix(String prefix) {
        try {
            Set<String> keys = redisTemplate.keys(RedisConstant.KEY_PREFIX_VALUE + prefix + "*");
            if (CollectionUtil.isNotEmpty(keys)) {
                redisTemplate.delete(keys);
                return true;
            }
            return true;
        } catch (Throwable t) {
            log.error("移除缓存失败前缀key[" + prefix + ", Codeor[" + t + "]");
        }
        return false;
    }

    /**
     * 递增
     *
     * @param key
     * @param delta
     * @return long
     */
    @Override
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        try {
            return redisTemplate.opsForValue().increment(key, delta);
        } catch (Throwable t) {
            log.error("递增缓存失败key[" + key + ", Codeor[" + t + "]");
        }
        return 0;
    }

    /**
     * 递减
     *
     * @param key
     * @param delta
     * @return long
     */
    @Override
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        try {
            return redisTemplate.opsForValue().decrement(key, delta);
        } catch (Throwable t) {
            log.error("递减缓存失败key[" + key + ", Codeor[" + t + "]");
        }
        return 0;
    }

    /**
     * 功能描述: 设置过期时间
     *
     * @param: key 键 time 时间(秒)
     * @return:
     * @auther: abs
     * @date: 2019/12/19 18:59
     */
    @Override
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Throwable t) {
            log.error("过期时间设置失败key[" + key + ", Codeor[" + t + "]");
        }
        return false;
    }

    /**
     * 功能描述: 查看过期时间
     *
     * @param: key 键 不能为null
     * @return: 时间(秒)
     * @auther: abs
     * @date: 2019/12/19 19:00
     */
    @Override
    public long getExpire(String key) {
        try {
            return redisTemplate.getExpire(key, TimeUnit.SECONDS);
        } catch (Throwable t) {
            log.error("过期时间获取失败key[" + key + ", Codeor[" + t + "]");
        }
        return -100;
    }

    /**
     * 查找匹配key (注意添加前缀)
     *
     * @param pattern key
     * @return /
     */
    @Override
    public List<String> scan(String pattern) {
        ScanOptions options = ScanOptions.scanOptions().match(pattern).build();
        RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
        RedisConnection rc = Objects.requireNonNull(factory).getConnection();
        Cursor<byte[]> cursor = rc.scan(options);
        List<String> result = new ArrayList<>();
        while (cursor.hasNext()) {
            result.add(new String(cursor.next()));
        }
        try {
            RedisConnectionUtils.releaseConnection(rc, factory);
        } catch (Throwable t) {
            log.error("过期时间获取失败key[" + pattern + ", Codeor[" + t + "]");
        }
        return result;
    }

    @Override
    public <T> T getObject(String key) {
        try {
            String k = RedisConstant.KEY_PREFIX_VALUE + key;
            return redisTemplate.execute((RedisCallback<T>) connection -> (T) SerializationUtils.deserialize(connection.get(k.getBytes())));
        } catch (Exception e) {
            log.error("getObject err key = {}", key, e);
            return null;
        }
    }

    @Override
    public void cacheObject(String key, Object value, long duration, TimeUnit unit) {
        try {
            String k = RedisConstant.KEY_PREFIX_VALUE + key;
            redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.setEx(k.getBytes(), unit.toSeconds(duration), Objects.requireNonNull(SerializationUtils.serialize(value))));
        } catch (Exception e) {
            log.error("cacheObject err key = {}", key, e);
        }

    }
}
