package com.iffy.mianshi.basic.fanxing.bianjie;

import java.util.ArrayList;

public class BianJieShangXianTestB {
    public static void main(String[] args) {
        //上界的list只能get，不能add（确切地说不能add出除null之外的对象，包括Object）
        /**
         * 只能用不能修改
         * */
        ArrayList<Animal> animal = new ArrayList<>();
        ArrayList<Cat> cat = new ArrayList<>();
        ArrayList<JiaFeiCat> jiaFeiCat= new ArrayList<>();
        //showAnimal(animal);
        showAnimal(cat);
        showAnimal(jiaFeiCat);
    }

    //上界 不能高于Cat 只接受Cat或者Cat的子类 并且只能读不能修改
    public static void showAnimal(ArrayList<? extends Cat> animal) {
        //animal.add(new Cat());//不能修改
        for (int i = 0; i < animal.size(); i++) {
            Cat cat = animal.get(i);//因为最大是Cat 所以可以使用Cat接收变量
            System.out.println(cat);
        }
    }


}
