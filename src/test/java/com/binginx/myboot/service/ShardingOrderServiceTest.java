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
public class ShardingOrderServiceTest {
    @Autowired
    private ShardingOrderService shardingOrderService;

    @Test
    public void testSave() {
        shardingOrderService.save();
    }
}
