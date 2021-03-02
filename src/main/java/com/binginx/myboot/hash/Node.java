package com.binginx.myboot.hash;

import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class Node {
    private String name;
    private String port;
    private String host;

    private Map<String,Object> data = new ConcurrentHashMap<String,Object>();

    public Node(String name, String port, String host) {
        this.name = name;
        this.port = port;
        this.host = host;
    }
    public void put(String key, Object value) {
        data.put(key, value);
    }

    public void remove(String key) {
        data.remove(key);
    }

    public Object get(String key) {
        return data.get(key);
    }

    public int dataSize(){
        return this.data.size();
    }
}
