package com.binginx.myboot.utils;

import com.binginx.myboot.model.Order;

import java.util.Optional;

public class OptionalTest {
    public static void main(String[] args) {
        Order order = new Order();
        Optional<Optional<Long>> s1 = Optional.ofNullable(order).map(OptionalTest::getOrderNumber);

        Optional<String> s2 = Optional.ofNullable(order).map(Order::getOrderName);

        Optional<Long> s3 = Optional.ofNullable(order).flatMap(OptionalTest::getOrderNumber);

        System.out.println(s1.get());
        System.out.println(s2.isPresent());
        System.out.println(s3.get());
    }

    private static Optional<Long> getOrderNumber(Order order) {
        return Optional.ofNullable(order).map(order1 -> order.getOrderId());
    }

}
