package com.ourline.framework;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RedisServiceImpl implements IRedisService{

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void put(String key, Object value, long seconds) {

        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    @Override
    public Object get(String key) {

        if (StringUtils.isNotBlank(key)) {

            return redisTemplate.opsForValue().get(key);
        } else {

            return "";
        }
    }

    @Override
    public boolean remove(String key) {

        return redisTemplate.delete(key);
    }

    @Override
    public long getExpire(String key) {

        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * @Title delete
     * @Description
     * @param key
     */
    @Override
    public void delete(String[] key) {

        Set<String> keys = Stream.of(key).map(k -> k).collect(Collectors.toSet());

        redisTemplate.delete(keys);
    }

    /**
     * @Title putHash
     * @Description 存放HashMap结构数据到Redis
     * @param key
     * @param m
     * @param timeout(单位秒)
     */
    @Override
    public void putHash(String key, HashMap<String, Object> m, long timeout) {

        redisTemplate.opsForHash().putAll(key, m);

        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * @Title getHash
     * @Description 获取hashmap
     * @param key
     * @return
     */
    @Override
    public HashMap<String, Object> getHash(String key) {

        Map<Object, Object> map = redisTemplate.opsForHash().entries(key);

        HashMap<String, Object> hashMap = new HashMap<>();

        map.forEach((k, v) -> hashMap.put((String) k, v));
        return hashMap;
    }

    /**
     * @Title getHashKey
     * @Description 获取指定Redis中hashkey的值
     * @param key     Redis key
     * @param hashKey map key
     * @return
     */
    @Override
    public Object getHashKey(String key, Object hashKey) {

        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * @Title hasHashKey
     * @Description Redis中指定hash结构是否存在key
     * @param key     Redis key
     * @param hashKey map key
     * @return
     */
    @Override
    public boolean hasHashKey(String key, Object hashKey) {

        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * @Title deleteHashKey
     * @Description 删除hashmap结构中的记录
     * @param key      Redis key
     * @param hashKeys map key
     */
    @Override
    public void deleteHashKey(String key, Object... hashKeys) {

        redisTemplate.opsForHash().delete(key, hashKeys);
    }

    /**
     * (non-javadoc)
     *
     */
    @Override
    public boolean hashKey(String key) {

        return redisTemplate.hasKey(key);
    }


    @Override
    public Long incr(String key,long delta){
        return redisTemplate
                .opsForValue().increment(key,delta);
    }

    @Override
    public Boolean expire(String key,long timeOut,TimeUnit unit){
        return redisTemplate.expire(key,timeOut,unit);
    }
}
