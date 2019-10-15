package com.iffy.mianshi.子类变量覆盖;

public class ClassTest {
    public static void main(String[] args) {
        ClassA a = new ClassB();
        System.out.println(a.num);
        a.printName();
        System.out.println(((ClassB) a).num);
        ((ClassB) a).printName();

        ClassB b = new ClassB();
        System.out.println(b.num);
        System.out.println(((ClassA) b).num);
    }

    public static class ClassA {
        public int num = 5;
        public void printName(){
            System.out.println("我是 ClassA");
        }
    }

    public static class ClassB extends ClassA {
        public int num = 10;
        public void printName(){
            System.out.println("我是 ClassB");
        }
    }

}




