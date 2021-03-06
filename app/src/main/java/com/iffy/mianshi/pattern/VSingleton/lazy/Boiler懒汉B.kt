package com.iffy.mianshi.pattern.VSingleton.lazy

//线程不安全
class Boiler懒汉B {
    //封闭构造函数
    private constructor()

    //由于Kotlin中没有 static关键字，所以静态方法放在companion object{}中
    companion object {
        private var mInstance: Boiler懒汉B? = null
        val instance: Boiler懒汉B?
            get() {
                println("getInstance")
                if (mInstance == null) {
                    mInstance =
                        Boiler懒汉B()
                }
                return mInstance
            }

    }


    fun b() {
        println(this)
    }

}


