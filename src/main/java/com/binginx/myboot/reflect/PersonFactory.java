package com.binginx.myboot.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PersonFactory<T> implements InvocationHandler {
    private T target;
    public T bind(T obj) {
        target = obj;
        return (T) Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
        System.out.println("start");
        Object result = method.invoke(target,objects);
        System.out.println("stop");
        return result;
    }
}
