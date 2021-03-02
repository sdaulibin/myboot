package com.binginx.myboot.service;

import com.binginx.myboot.model.GenConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class GenCodeServiceTest {
    @Autowired
    private GeneratorService generatorService;

    @Test
    public void testGenerateCode() {
        GenConfig genConfig = new GenConfig();
        genConfig.setTableName("users");
        generatorService.generatorCode(genConfig);
    }
}
