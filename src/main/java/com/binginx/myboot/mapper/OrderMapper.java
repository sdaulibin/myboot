package com.binginx.myboot.mapper;

import com.binginx.myboot.model.OrderEntity;

import java.util.List;

public interface OrderMapper {
    void insert(OrderEntity order);
    void delete(Long orderId);
    List<OrderEntity> selectAll();
    List<OrderEntity> selectRange();
}
