package com.iffy.mianshi.basic.duotai;

public class DuoTai {
    public static void main(String[] args) {
        Father f = new Father();
        f.say();//我是爸爸
        System.out.println("Father的名字应该是张飞：" + f.name);//张飞

        Son s = new Son();
        s.say();//我是儿子
        System.out.println("son的名字应该是张苞：" + s.name);//张苞

        Father ff = new Son();
        ff.say();//我是儿子
        System.out.println("ff多态的名字应该是张飞：" + ff.name);//张飞
    }

    static class Father {
        private static String name = "张飞";
        public void say() {
            System.out.println("我是爸爸");
        }
    }

    static class Son extends Father {
        private String name = "张苞";
        public void say() {//“重写”只能适用于实例方法，不能用于静态方法
            System.out.println("我是儿子");
        }
    }
}
