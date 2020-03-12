package com.iffy.mianshi.basic.fanxing.leixingxianding;

/**
 * author : iffy
 * time   : 2020/03/11
 */
//实现了比较方法
public class Student implements Comparable {
    private final String name;
    private final int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public int compareTo(Object o) {
        if (age > ((Student) o).age) {
            return 1;
        } else if (age == ((Student) o).age) {
            return 0;
        } else if (age < ((Student) o).age) {
            return -1;
        }
        return -1;
    }
}