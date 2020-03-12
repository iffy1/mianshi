package com.iffy.mianshi.basic.fanxing.rx;

/**
 * author : iffy
 * time   : 2020/03/12
 */
//泛型声明在类上 类里面都可以使用
public class ImplObservable<T> implements Observable<T> {
    T t;

    public ImplObservable(T t) {
        this.t = t;
    }

    public static <T> Observable<T> create(T t) {
        return new ImplObservable<>(t);
    }

    @Override
    public T call() {
        return null;
    }

    @Override
    public <U> Observable<U> map(Func1<T, U> func1) {
        Observable<U> trans = create(func1.trans(t));
        return trans;
    }

    @Override
    public void doOnNext(Action<T> action) {
        action.CallAction(t);
    }


}
