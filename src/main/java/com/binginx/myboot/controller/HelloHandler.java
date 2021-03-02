package com.binginx.myboot.controller;

import cn.hutool.core.util.RandomUtil;
import com.binginx.myboot.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/hello")
public class HelloHandler {
    @RequestMapping(value = "/test1/{id}",method = RequestMethod.GET)
    public String test1(@PathVariable String id) {
        log.info("id:{}",id);
        return ResponseEntity.ok("hello test1:"+id).toString();
    }
    @RequestMapping(value = "/test2/{id}",method = RequestMethod.GET)
    public String test2(@PathVariable String id) {
        log.info("id:{}",id);
        return ResponseEntity.ok("hello test2:"+id).toString();
    }
}
