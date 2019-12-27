package com.iffy.mianshi.temp;


public class Test {

    public static void main(String[] args) {
        String [] a = new String[]{"a"};

        test(a);

        testB("b");


    }

    public  static void test(String args[]){
        System.out.println(args[0]);

    }

    public static void testB(String t){
        System.out.println(t);
    }

}
