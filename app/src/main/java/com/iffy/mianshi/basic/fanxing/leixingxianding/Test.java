package com.iffy.mianshi.basic.fanxing.leixingxianding;

import java.io.File;
import java.time.format.TextStyle;

/**
 * author : iffy
 * time   : 2020/03/11
 */
public class Test {
    public static void main(String[] args) {
        //老师没有实现比较接口 所以不能传入
        Teacher teacherA = new Teacher("张三", 12);
        Teacher teacherB = new Teacher("李四", 11);
        //FanXingLeiXingXianDing.bijiao(teacherA,teacherB);

        Student studentA = new Student("张三", 15);
        Student studentB = new Student("李四", 13);
        int result = FanXingLeiXingXianDing.bijiao(studentA,studentB);
        System.out.println("比较结果是："+result);
    }
}
