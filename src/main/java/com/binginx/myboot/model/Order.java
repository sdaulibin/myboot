package com.binginx.myboot.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order {
    private long orderId;
    private String orderName;
    private BigDecimal orderPrice;
    private String userId;
}
