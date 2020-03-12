package com.iffy.mianshi.pattern.proxy.dongtaidaili;

/**
 * author : iffy
 * time   : 2020/03/12
 */
public class Student implements IStudy_service {
    @Override
    public void study(String s) {
    }

    @Override
    public void sing(String song) {
    }

    @Override
    public String whoAreU() {
        return "张三";
    }
}
