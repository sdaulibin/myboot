package com.binginx.myboot.model;

import lombok.Data;

@Data
public class Person {
    private long personId;
    private String name;
    private int age;
    private String address;
}
