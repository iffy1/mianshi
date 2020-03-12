package com.iffy.mianshi.basic.fanxing.leixingxianding;

/**
 * author : iffy
 * time   : 2020/03/11
 */
public final class FanXingLeiXingXianDing {
    //限定T必须继承或者实现 Comparable
    public static <T extends Comparable> int bijiao(T a, T b) {
        return a.compareTo(b);
    }
}

