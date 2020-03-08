package com.iffy.mianshi.basic.fanshe;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * author : iffy
 * time   : 2020/03/04
 */
public class FanShe {
    public static void main(String[] args) {
        //获取Filed
        try {
            //反射获取类
            Class clazz = Class.forName("com.iffy.mianshi.basic.fanshe.Monkey");
            //获取变量
            Field f = clazz.getDeclaredField("nickName");
            //实例化对象
            Object monkey = clazz.newInstance();
            //设置变量
            f.set(monkey,"我是狗");

            System.out.println(f.get(monkey));
            System.out.println(((Monkey)monkey).nickName);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //获取调用method
        try {
            //根据类名称 把类加载进内存 产生class对象
            Class cl = Class.forName("com.iffy.mianshi.basic.fanshe.Monkey");

            //打印所有的method
            for (Method m : cl.getDeclaredMethods()) {
                System.out.println(m);
            }

            //创建monkey实例
            Object monkey = cl.newInstance();

            //获取setName方法 (方法名,参数类型)
            Method setMethod = cl.getDeclaredMethod("setName", String.class);
            //调用monkey类的setName方法
            setMethod.invoke(monkey, "我是猴子");

            //获取getName方法 这个方法没有参数 所以传null
            Method getMethod = cl.getDeclaredMethod("getName", null);
            //调用monkey的getName方法
            Object result = getMethod.invoke(monkey, null);

            System.out.println(result);
            System.out.println(((Monkey) monkey).getName());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
