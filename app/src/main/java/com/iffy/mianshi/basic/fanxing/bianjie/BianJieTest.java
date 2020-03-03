package com.iffy.mianshi.basic.fanxing.bianjie;

import java.util.ArrayList;

public class BianJieTest {
    public static void main(String[] args) {

        //下界的list只能add，不能get

        //上界的list只能get，不能add（确切地说不能add出除null之外的对象，包括Object）
        /**
         * 只能用不能修改
        * */

        ArrayList<JiaFeiCat> jiafeiList = new ArrayList<>();
        jiafeiList.add(new JiaFeiCat());

        ArrayList<Cat> catList = new ArrayList<>();
        catList.add(new Cat());

        ArrayList<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal());

        ArrayList<Dog> dogList = new ArrayList<>();
        dogList.add(new Dog());

        addCatSuper(animalList);   addCatExtends(jiafeiList);
        addCatSuper(catList);      addCatExtends(catList);
        //addCatSuper(jiafeiList);  addCatExtends(animalList);
        //addCatSuper(dogList);      addCatExtends(dogList);
    }
    //下界 不能低于Cat 只接受Cat或者父类animal
    private static void addCatSuper(ArrayList<? super Cat> cat){
        cat.add(new Cat());
        Cat c = (Cat)cat.get(0);
    }

    //上界 不能高于Cat 只接受Cat或者Cat的子类 并且只能读不能修改
    private static void addCatExtends(ArrayList<? extends Cat> cat){
        //cat.add(new Cat());
        Cat c = cat.get(0);
    }



}
