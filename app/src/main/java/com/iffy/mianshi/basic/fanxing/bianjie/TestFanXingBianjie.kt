package com.iffy.mianshi.basic.fanxing.bianjie

import java.util.*

class TestFanXingBianjie {

    fun main() {

        val jiafeiList = ArrayList<JiaFeiCat>()
        jiafeiList.add(JiaFeiCat())

        val catList = ArrayList<Cat>()
        catList.add(Cat())

        val animalList = ArrayList<Animal>()
        animalList.add(Animal())

        val dogList = ArrayList<Dog>()
        dogList.add(Dog())

        //addCatSuper(jiafeiList)
        addCatSuper(catList)
        addCatSuper(animalList)
        //addCatSuper(dogList)

        addCatExtends(jiafeiList)
        addCatExtends(catList)
        //addCatExtends(animalList)
        //addCatExtends(dogList)
    }

    //下界 不能低于Cat 只接受Cat或者父类animal
    fun addCatSuper(cat: ArrayList<in Cat>) {
        cat.add(Cat())
        val c = cat[0] as Cat
    }

    //上界 不能高于Cat 只接受Cat或者Cat的子类 并且只能读不能修改
    fun addCatExtends(cat: ArrayList<out Cat>) {
        //cat.add(Cat())
        val c = cat[0]
    }
}




