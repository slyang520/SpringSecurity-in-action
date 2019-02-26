package com.example.slyangsecurity.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private  ValueOperations<String, Object> valueOperations; //String（字符串）

    private final static long DEFAULT_CACHE_TIME_DAY = 3;

    /**
     * 缓存 对象JSON 序列化
     * @param key
     * @param value
     * @param timeout
     * @param unit
     */
    public void cacheObjectToJson(String key, Object value, long timeout, TimeUnit unit){
        valueOperations.set(key,value,timeout ,unit);
    }

    /**
     * 缓存 对象JSON 序列化
     * @param key
     * @param value
     */
    public void cacheObjectToJson(String key, Object value){
        cacheObjectToJson(key, value,DEFAULT_CACHE_TIME_DAY,TimeUnit.DAYS);
    }

    /**
     * 获取缓存对象
     * 字符串 JSON反序列化
     * @param key
     */
    @SuppressWarnings("unchecked")
    public <T> T  getCacheObject(String key){
        return (T) valueOperations.get(key);
    }

    /**
     * 删除
     * @param key
     * @return
     */
    public Boolean  delByKey(String key){
        return valueOperations.getOperations().delete(key);
    }

    /**
     * 批量删除
     * @param keys
     * @return
     */
    public Long batchDelByKeys(Collection<String> keys){
        return valueOperations.getOperations().delete(keys);
    }

}
