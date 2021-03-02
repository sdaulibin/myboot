package com.binginx.myboot.utils;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;

@Service
public class CaffeineCache {

    @Autowired
    private CacheManager cacheManager;

    @Value("${spring.cache.cache-names}")
    private String cacheName;

    /*@Cacheable(value = "trans_doc",key = "#key")
    public String get(String key,String value) {
        return value;
    }

    @CacheEvict(value = "trans_doc",key = "#key")
    public void delete(String key) {
    }*/

    public String get(String key) {
        Cache cache = cacheManager.getCache(cacheName);
        if (!ObjectUtil.isEmpty(cache)) {
            Cache.ValueWrapper valueWrapper = cache.get(key);
            if(!ObjectUtil.isEmpty(valueWrapper)) {
                if (valueWrapper.get() instanceof String) {
                    return (String) valueWrapper.get();
                }
            }
        }
        return null;
    }

    public void put(String key,String value) {
        Cache cache = cacheManager.getCache(cacheName);
        if (!ObjectUtil.isEmpty(cache)) {
            cache.put(key, value);
        }
    }

    public boolean remove(String key) {
        Cache cache = cacheManager.getCache(cacheName);
        if (!ObjectUtil.isEmpty(cache)) {
            return cache.evictIfPresent(key);
        }
        return true;
    }
}
