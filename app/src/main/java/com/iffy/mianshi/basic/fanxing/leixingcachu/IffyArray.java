package com.iffy.mianshi.basic.fanxing.leixingcachu;

/**
 * author : iffy
 * time   : 2020/03/11
 */
public class IffyArray<T> {
    private Object[] objects = new Object[10];
    private int mSize = 0;

    public void add(Object o) {
        objects[mSize++] = o;
    }

    public <T> T get(int i) {
        return (T) objects[i];//这里不是强转 不是T类型也不会报异常
    }
}
