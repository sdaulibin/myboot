package com.binginx.myboot.service;

import com.alibaba.excel.EasyExcel;
import com.binginx.myboot.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class EasyExcelTest {
    @Test
    public void testValueOfMap() throws FileNotFoundException {
        List<Person> downloadDatas = new ArrayList<Person>();
        for (int i = 0; i < 20; i++) {
            Person person = new Person();
            person.setAddress("山东省青岛市"+i);
            person.setAge(20+i);
            person.setName("张三"+i);
            downloadDatas.add(person);
        }
        OutputStream outputStream = new FileOutputStream("/Users/binginx/Downloads/easyexceltest.xlsx");
        EasyExcel.write(outputStream, Person.class).sheet("短信任务明细").doWrite(downloadDatas);
    }
}
