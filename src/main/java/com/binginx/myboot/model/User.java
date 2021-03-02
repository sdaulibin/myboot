package com.binginx.myboot.model;

import lombok.Data;

@Data
public class User {
    private long id;
    private String userName;
    private String password;
    private String mobilePhone;
}
