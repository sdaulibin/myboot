package com.binginx.myboot.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CaffeineCacheConfig {
    @Value("${spring.cache.cache-names}")
    private List<String> cacheName;
    @Bean
    public CacheManager cacheManeager() {
        Caffeine caffeine = Caffeine.newBuilder()
                .initialCapacity(500).maximumSize(3000)
                .expireAfterWrite(1800, TimeUnit.SECONDS)
                .expireAfterAccess(180,TimeUnit.SECONDS);
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        caffeineCacheManager.setCacheNames(cacheName);
        caffeineCacheManager.setAllowNullValues(true);
        return caffeineCacheManager;
    }
}
