package com.binginx.myboot.mapper;

import com.binginx.myboot.model.OrderItemEntity;

import java.util.List;

public interface OrderItemMapper {
    void insert(OrderItemEntity orderItemEntity);
    void delete(Long orderId);
    List<OrderItemEntity> selectAll();
    List<OrderItemEntity> selectRange();
}
