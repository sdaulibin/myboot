package com.binginx.myboot.service;

import com.binginx.myboot.mapper.PersonMapper;
import com.binginx.myboot.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceTest {
    @Autowired
    private PersonMapper personMapper;
    @Test
    public void testInsert() {
        for (int i = 0; i < 20; i++) {
            Person person = new Person();
            person.setAddress("山东省青岛市"+i);
            person.setAge(20+i);
            person.setName("张三"+i);
            personMapper.insert(person);
        }
    }
    @Test
    public void testDelete() {
        personMapper.delete(469927240814034945L);
    }

    @Test
    public void testQuery() {
        List<Long> personIds = new ArrayList<>(8);
        personIds.add(469928188353445889L);
        personIds.add(469928188953231361L);
        personIds.add(469928189037117441L);
        //personIds.add(469928188974202880L);
        //personIds.add(469928189028728832L);
        //personIds.add(469928188915482624L);
        List<Person> personList = personMapper.selectBypersonIds(personIds);
        Assert.assertEquals(personList.size(),6);
        for (Person person:personList) {
            log.info(person.toString());
        }
    }
}
