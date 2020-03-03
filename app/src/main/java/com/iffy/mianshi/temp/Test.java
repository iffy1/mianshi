package com.iffy.mianshi.temp;


import java.util.HashMap;

public class Test {

    public static void main(String[] args) {
        String [] a = new String[]{"a"};


        test(a);

        testB("b");
        printInt();




    }

    public static void printInt(){
        for(int i=30;i<33;i++){
            System.out.println("1左移"+i+"的结果为"+ (1<<i));
        }
        System.out.println("Integer.MAX_VALUE"+Integer.MAX_VALUE);
    }

    public  static void test(String args[]){
        System.out.println(args[0]);

    }

    public static void testB(String t){
        System.out.println(t);
    }

}
