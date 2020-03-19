package com.iffy.mianshi.dataStructure.list;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * author : iffy
 * time   : 2020/03/15
 */
public class HashMapTest {
    static HashMap<String, String> map = new HashMap();

    public static void main(String[] args) {
        for (Map.Entry<String,String> s:map.entrySet()){
            s.getKey();
            s.getValue();
        }
    }
}
