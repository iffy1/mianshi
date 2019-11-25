package com.iffy.mianshi.pattern.VIIITemplate.animal.template

//模板类
abstract class Actions {

    fun start(){
        openDoor()
        putInTheAnimal()
        closeDoor()
    }

    //通用步骤
    private fun openDoor() {
        println("打开冰箱门")
    }
    //根据各种动物覆盖实现
    abstract fun putInTheAnimal()
    //通用步骤
    private fun closeDoor() {
        println("关上冰箱门")
    }
}