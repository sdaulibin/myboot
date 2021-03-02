package com.binginx.myboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.binginx.myboot.mapper","com.binginx.myboot.generator.mapper"})
public class MybootApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybootApplication.class, args);
    }

}
