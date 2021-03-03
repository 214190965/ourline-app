package com.ourline.framework;


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * redis 操作接口类
 *
 */
public interface IRedisService {


    /**
     * redis 存储
     *
     * @param key
     * @param value
     * @param seconds 超时时间(秒)
     */
    void put(String key, Object value, long seconds);

    /**
     * Redis 存放的键值对
     *
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 根据key删除获取Redis 存放的键值对
     *
     * @param key
     * @return
     */
    boolean remove(String key);

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    long getExpire(String key);

    /**
     * @Title delete
     * @Description 批量删除
     * @param key
     */
    void delete(String[] key);

    /**
     * @Title putHash
     * @Description TODO
     * @param key
     * @param m
     * @param timeout
     */
    void putHash(String key, HashMap<String, Object> m, long timeout);

    /**
     * @Title getHashKey
     * @Description TODO
     * @param key
     * @param hashKey
     * @return
     */
    Object getHashKey(String key, Object hashKey);

    /**
     * @Title hasHashKey
     * @Description TODO
     * @param key
     * @param hashKey
     * @return
     */
    boolean hasHashKey(String key, Object hashKey);

    /**
     * @Title deleteHashKey
     * @Description TODO
     * @param key
     * @param hashKeys
     */
    void deleteHashKey(String key, Object... hashKeys);

    /**
     * @Title getHash
     * @Description TODO
     * @param key
     * @return
     */
    HashMap<String, Object> getHash(String key);

    /**
     * @Title hashKey
     * @Description redis 是否包含key
     * @param key
     * @return
     */
    boolean hashKey(String key);

    /**
     * 递增
     * @param key
     * @param delta
     * @return
     */
    Long incr(String key,long delta);

    /**
     * 设置过期时间
     * @param key key值
     * @param timeOut 时间
     * @param unit 时间单位
     * @return
     */
    Boolean expire(String key, long timeOut, TimeUnit unit);
}
