package com.iffy.mianshi.basic.fanxing.basic;

/**
 * author : iffy
 * time   : 2020/03/11
 */
public class FanXingLeiB<T> {
    private T value;

    //参数类型
    public FanXingLeiB(T v) {
        value = v;
    }

    //参数类型
    public void setValue(T value) {
        this.value = value;
    }

    //返回值类型
    public T getValue() {
        return value;
    }

}
