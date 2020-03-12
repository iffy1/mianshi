package com.iffy.mianshi.basic.fanxing.rx;

/**
 * author : iffy
 * time   : 2020/03/12
 */
//将泛型声明在类上
public interface Observable<T> {
    T call();

    <U> Observable<U> map(Func1<T, U> func1);

    //Observable<T> doOnNext(Action<T> action);

    void doOnNext(Action<T> action);
}
