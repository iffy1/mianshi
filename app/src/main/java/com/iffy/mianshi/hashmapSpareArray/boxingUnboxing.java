package com.iffy.mianshi.hashmapSpareArray;

import java.util.ArrayList;

public class boxingUnboxing {
    public void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(1); //自动装箱 auto boxing a.add(new Integer(1));
        a.add(2);//自动装箱 auto boxing a.add(new Integer(1));

        int i = a.get(0);//自动拆箱 unboxing
    }
}
