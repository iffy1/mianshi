package com.iffy.mianshi.basic.duotai;

public class Test {   //例子3
    public static void main(String[] args) {
        Person p = new Man();
        System.out.println(p.type);        //返回结果为P
        System.out.println(p.getName());   //返回结果为Man
    }
}

class Person {
    String type = "P";
    public String getName() {
        String name = "Person";
        return name;
    }
}

class Man extends Person {
    String type = "M";
    public String getName() {
        String name = "Man";
        return name;
    }
}