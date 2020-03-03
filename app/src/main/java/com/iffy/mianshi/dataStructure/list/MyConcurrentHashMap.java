package com.iffy.mianshi.dataStructure.list;

import java.util.concurrent.ConcurrentHashMap;

public class MyConcurrentHashMap {

    public static void main(String[] args) {
        ConcurrentHashMap cm = new ConcurrentHashMap<String,String>();
        cm.put("","");

        System.out.println(1 << 16);
        int a = 1;
        for(int i = 0;i<10;i++){
            a <<= 1;
            System.out.println(a);
        }
        System.out.println(1 << 30);

    }
}
