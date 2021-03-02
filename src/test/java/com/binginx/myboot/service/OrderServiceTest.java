package com.binginx.myboot.service;

import cn.hutool.core.util.RandomUtil;
import com.binginx.myboot.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void testGenerateOrder() {
        for (int i = 0; i < 100; i++) {
            Order order = new Order();
            order.setOrderId(RandomUtil.randomLong(99999999));
            orderService.generateOrder(order);
        }
    }

    @Test
    public void testAsyncCancleOrder() {
        for (int i = 0; i <100; i++) {
            orderService.asyncCancleOrder(RandomUtil.randomLong(99999999));
        }
    }

    @Test
    public void testValueOfMap() {
        orderService.valueOfMap();
    }
}
