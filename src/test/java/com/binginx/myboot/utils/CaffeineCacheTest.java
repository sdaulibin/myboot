package com.binginx.myboot.utils;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.DefaultConnectionFactory;
import net.spy.memcached.MemcachedClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CaffeineCacheTest {

    @Autowired
    private CaffeineCache caffeineCache;

    @Test
    public void testMemCache() throws IOException {
        log.info("getOperationTimeout():{}",new MemcachedClient(new DefaultConnectionFactory() {
            @Override
            public long getOperationTimeout() {
                return 123;
            }
        }, AddrUtil.getAddresses("127.0.0.1:8080")).getOperationTimeout());
    }

    @Test
    public void testGet() {
        String key  = "libin";
        String value = "hello world";
        caffeineCache.put(key,value);
        log.info("get key {},result {}",key,caffeineCache.get(key));
        log.info("remove key {},result {}",key,caffeineCache.remove(key));
        log.info("get key {},result {}",key,caffeineCache.get(key));
    }
    @Test
    public void testCache() {
        String keyReq  = "andy";
        String valueReq = "hello world andy";
        String keyRes  = "binginx";
        String valueRes = "hello world binginx";

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                String key = "key";
                String value = caffeineCache.get(key);
                if(StrUtil.isNotEmpty(value)) {
                    log.info("thread name:{},reqValue:{},resValue:{}",Thread.currentThread().getName(),valueReq,value);
                } else {
                    log.info("response thread not put value");
                    caffeineCache.put(key,valueReq);
                }
            }).start();
        }
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                String key = "key";
                String value = caffeineCache.get(key);
                if(StrUtil.isNotEmpty(value)) {
                    log.info("thread name:{},reqValue:{},resValue:{}",Thread.currentThread().getName(),value,valueRes);
                } else {
                    log.info("request thread not put value");
                    caffeineCache.put(key,valueRes);
                }
            }).start();
        }
    }
}
