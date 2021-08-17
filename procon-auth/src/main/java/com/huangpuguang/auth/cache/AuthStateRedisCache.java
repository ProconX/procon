package com.huangpuguang.auth.cache;


import com.huangpuguang.common.redis.service.RedisService;
import me.zhyd.oauth.cache.AuthCacheConfig;
import me.zhyd.oauth.cache.AuthStateCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 扩展Redis版的state缓存
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @date 2019/10/24 13:38
 * @since 1.8
 */
@Component
public class AuthStateRedisCache implements AuthStateCache {

    @Autowired
    private RedisService redisService;

    /**
     * 存入缓存，默认3分钟
     *
     * @param key   缓存key
     * @param value 缓存内容
     */
    @Override
    public void cache(String key, String value) {
        redisService.setCacheObject(key, value, AuthCacheConfig.timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * 存入缓存
     *
     * @param key     缓存key
     * @param value   缓存内容
     * @param timeout 指定缓存过期时间（毫秒）
     */
    @Override
    public void cache(String key, String value, long timeout) {
        redisService.setCacheObject(key, value, timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * 获取缓存内容
     *
     * @param key 缓存key
     * @return 缓存内容
     */
    @Override
    public String get(String key) {
        return redisService.getCacheObject(key);
    }

    /**
     * 是否存在key，如果对应key的value值已过期，也返回false
     *
     * @param key 缓存key
     * @return true：存在key，并且value没过期；false：key不存在或者已过期
     */
    @Override
    public boolean containsKey(String key) {

        return redisService.hasKey(key);
    }
}