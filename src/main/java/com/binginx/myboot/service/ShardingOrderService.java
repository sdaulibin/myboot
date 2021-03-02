package com.binginx.myboot.service;

import com.binginx.myboot.mapper.OrderItemMapper;
import com.binginx.myboot.mapper.OrderMapper;
import com.binginx.myboot.model.OrderEntity;
import com.binginx.myboot.model.OrderItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShardingOrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderItemMapper orderItemMapper;

    public void save() {
        System.out.println("---------------------------- Insert Data ----------------------------");
        List<Long> result = new ArrayList<>(10);
        for (int i = 11; i <= 10000; i++) {
            OrderEntity order = new OrderEntity();
            order.setUserId(i);
            order.setStatus("INSERT_TEST_JPA");
            orderMapper.insert(order);
            OrderItemEntity item = new OrderItemEntity();
            item.setOrderId(order.getOrderId());
            item.setUserId(i);
            item.setStatus("INSERT_TEST_JPA");
            orderItemMapper.insert(item);
            result.add(order.getOrderId());
        }

    }
}
