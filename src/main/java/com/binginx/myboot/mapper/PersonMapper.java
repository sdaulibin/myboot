package com.binginx.myboot.mapper;

import com.binginx.myboot.model.OrderEntity;
import com.binginx.myboot.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonMapper {
    void insert(Person person);
    void delete(Long personId);
    List<Person> selectBypersonIds(List<Long> personIds);
}
