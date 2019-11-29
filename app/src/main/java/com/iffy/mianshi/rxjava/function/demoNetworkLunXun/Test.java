package com.iffy.mianshi.rxjava.function.demoNetworkLunXun;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

class Test {
    static String result = "";

    public static void main(String[] args) {
        repeatWhen();
        try {
            Thread.sleep(20000);
        } catch (Exception e) {

        }
    }

    //repeatWhen ：符合条件才执行下一次
    // 设置下次执行的时间，这种方式只能执行两次。
    private static void repeatWhen() {
        Disposable observable =
                Observable.just("A").repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Object> objectObservable) {
                     return  objectObservable.flatMap(new Function<Object, ObservableSource<Long>>() {
                         @Override
                         public ObservableSource<Long> apply(Object o) {
                             return Observable.timer(1000, TimeUnit.MILLISECONDS);
                         }
                        });
                    }
                })
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) {
                                result = s;
                                System.out.println(s);
                            }
                        });
    }

}
