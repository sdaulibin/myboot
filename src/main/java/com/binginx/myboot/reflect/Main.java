package com.binginx.myboot.reflect;

public class Main {
    public static void main(String[] args) {
        PersonFactory personFactory = new PersonFactory();
        Person man = (Person) personFactory.bind(new Man());
        man.say("hello world");
    }
}
