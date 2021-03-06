package com.iffy.mianshi.basic.fanxing.leixingcachu;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

//https://www.cnblogs.com/wuqinglong/p/9456193.html
public class LeiXingCaChu {
    public static void main(String[] args) {
        //不指定泛型的话 是可以随意添加Object的
        //    ArrayList list = new ArrayList();
        //    list.add(1)
        //    list.add("121")
        //    list.add(new Date())

        /**
         * 代码中定义List<Object>和List<String>等类型，在编译后都会变成List
         * java在编译的时候会擦除类型
         * get的时候 不会自动强转 不然反射插入的不同类型会报异常
         *     public E get(int index) {
         *         if (index >= size)
         *             throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
         *              //这块不是强转 不然会报异常
         *         return (E) elementData[index];
         *     }
         * */

        ArrayList list = new ArrayList<Integer>();//不定义引用的泛型的话是不会检查出错误的
        list.add("");
        list.get(0); //获取的时候获取的是Object类 应为定义的时候没定义类型

        ceshi1();
        ceshi2();
        ceshi3();


    }

    //测试1打印list的类型
    static void ceshi1() {

        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(111);

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("iffy");

        //IntList存储的对象是：class java.util.ArrayList
        System.out.println("IntList存储的对象是：" + intList.getClass());

        //StringList存储的对象是：class java.util.ArrayList
        System.out.println("StringList存储的对象是：" + stringList.getClass());
    }

    //测试2 通过反射插入非泛型要求的类
    private static void ceshi2() {
        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(111);
        try {
            Method add = intList.getClass().getMethod("add", Object.class);
            add.invoke(intList, "iffy");

            add.invoke(intList, 4.0f);
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("intList存储了：" + intList.size() + "个对象");
        System.out.println("intList存储1的内容是：" + intList.get(0));
        System.out.println("intList存储2的内容是：" + intList.get(1));
        System.out.println("intList存储3的内容是：" + intList.get(2));
    }

    private static void ceshi3() {
        IffyArray iffyArray = new IffyArray<Integer>();
        iffyArray.add(123);
        iffyArray.add("iffy");
        iffyArray.add(3.0f);

        System.out.println(iffyArray.get(0));
        System.out.println(iffyArray.get(1));
        System.out.println(iffyArray.get(2));
    }

}
