package com.binginx.myboot.service;

import com.binginx.myboot.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Service
public class OrderService {
    private static Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Value("#{${myboot.value.map}}")
    public Map<String,String> map;
    @Value("#{${myboot.value.map2}}")
    public Map<String,Map<String,String>> map2;
    @Value("#{${myboot.value.map3}}")
    public Map<String,Map<String,String>> map3;
    public static ReentrantLock reentrantLock = new ReentrantLock(true);
    public void generateOrder(Order order) {
        log.info("我进入到了生成订单的方法===================:{}",order.getOrderId());
        reentrantLock.lock();
        try {
            log.info("我获得了生成订单的锁>>>>>>>>>>>>:{}",order.getOrderId());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.info("catch interruptedException");
        } finally {
            log.info("我释放了了生成订单的锁>>>>>>>>>>>>:{}",order.getOrderId());
            reentrantLock.unlock();
        }
    }

    @Async("bootAsyncExecutor")
    public void asyncCancleOrder(long orderId) {
        log.info("{} 异步执行任务执行任务,取消订单{}",Thread.currentThread().getName(),orderId);
    }

    public void valueOfMap() {
        logger.info(map.toString());
        logger.info(map.get("mciCluster1"));
        logger.info(map.get("mciCluster2"));
        logger.info(map.get("mciCluster3"));
        logger.info(map.get("mciCluster4"));
        logger.info(map2.toString());
        logger.info(map2.get("1").get("191001"));
        logger.info(map3.toString());
        log.info(map3.get("191001").get("2"));
    }
}
