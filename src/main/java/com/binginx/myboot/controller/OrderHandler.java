package com.binginx.myboot.controller;

import cn.hutool.core.util.RandomUtil;
import com.binginx.myboot.model.Order;
import com.binginx.myboot.service.OrderService;
import com.binginx.myboot.service.ShardingOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Slf4j
@RestController
@RequestMapping(value = "/order")
public class OrderHandler {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ShardingOrderService shardingOrderService;

    @RequestMapping(value = "/genOrder/{id}",method = RequestMethod.GET)
    public String genOrder(@PathVariable String id) {
        //log.info("id:{}",id);
        Order order = new Order();
        order.setOrderId(RandomUtil.randomLong(99999999));
        orderService.generateOrder(order);
        return ResponseEntity.ok("hello world").toString();
    }

    @RequestMapping(value = "/async/{id}",method = RequestMethod.GET)
    public String async(@PathVariable String id) {
        orderService.asyncCancleOrder(RandomUtil.randomLong(99999999));
        return ResponseEntity.ok("hello world").toString();
    }

    @GetMapping(value = "/save")
    public String save() {
        shardingOrderService.save();
        return "插入成功";
    }
}
