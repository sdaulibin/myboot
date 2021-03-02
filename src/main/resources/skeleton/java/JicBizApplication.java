package com.jic.manager;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.jic.common.swagger.config.Swagger2Config;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@ComponentScan({"com.jic.manager","com.jic.common"})
@SpringBootApplication
@EnableTransactionManagement
@EnableEurekaClient
@EnableSwagger2
@MapperScan("com.jic.manager.mapper")
@EnableSwaggerBootstrapUI
@EnableDiscoveryClient
public class JicBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(JicBizApplication.class, args);
    }

    @Bean
    @ConditionalOnMissingBean
    public Docket getDocket(){
        return new Swagger2Config().createRestApi(this.getClass().getSimpleName());
    }

    /**
     * 时区设置
     */
    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }
}
