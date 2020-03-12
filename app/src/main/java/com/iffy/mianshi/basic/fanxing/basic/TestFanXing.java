package com.iffy.mianshi.basic.fanxing.basic;

import android.os.Build;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * author : iffy
 * time   : 2020/03/12
 */

public class TestFanXing {
    public static void main(String[] args) {
        test();
    }

    public static void test(){
        System.out.println("Start");
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("123");//泛型就是为了解决数据存储的安全性
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println((Integer)iterator.next());
        }
        System.out.println("End");
    }

}
