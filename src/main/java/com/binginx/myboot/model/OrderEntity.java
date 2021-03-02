package com.binginx.myboot.model;

import lombok.Data;

@Data
public class OrderEntity {
    private Long orderId;
    private Integer userId;
    private String status;
}
