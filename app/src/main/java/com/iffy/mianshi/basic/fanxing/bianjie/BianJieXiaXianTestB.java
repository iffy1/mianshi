package com.iffy.mianshi.basic.fanxing.bianjie;

import java.util.ArrayList;

public class BianJieXiaXianTestB {
    public static void main(String[] args) {
        //上界的list只能get，不能add（确切地说不能add出除null之外的对象，包括Object）
        /**
         * 只能用不能修改
         * */
        ArrayList<Animal> animal = new ArrayList<>();
        ArrayList<Cat> cat = new ArrayList<>();
        ArrayList<JiaFeiCat> jiaFeiCat= new ArrayList<>();
        showAnimal(animal);
        showAnimal(cat);
        //showAnimal(jiaFeiCat);
    }

    //上界 不能高于Cat 只接受Cat或者Cat的父类 不能读取 只能修改
    public static void showAnimal(ArrayList<? super Cat> animal) {
        for (int i = 0; i < animal.size(); i++) {
           // Animal cat = animal.get(i);//最小是Cat 只能用Object类接收 所以不能读取
            //System.out.println(cat);
        }
    }


}
