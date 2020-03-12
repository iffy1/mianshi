package com.iffy.mianshi.basic.fanxing.rx;

/**
 * author : iffy
 * time   : 2020/03/12
 */
public class Test {
    public static void main(String[] args) {
        ImplObservable<Student> observable = new ImplObservable<>(new Student());
        Student s = observable.call();

        //手写RXJAVA的用法
        Observable<Student> ob = ImplObservable.create(new Student());

        //将Student转换为Teacher
        ob.map(new Func1<Student, Teacher>() {
            @Override
            public Teacher trans(Student student) {
                Teacher t = new Teacher();
                System.out.println("A"+t);
                return t;
            }
        }).doOnNext(new Action<Teacher>() {
            @Override
            public void CallAction(Teacher teacher) {
                System.out.println("B"+teacher);
            }
        });


    }

    public static class Student {

    }

    public static class Teacher {

    }
}


