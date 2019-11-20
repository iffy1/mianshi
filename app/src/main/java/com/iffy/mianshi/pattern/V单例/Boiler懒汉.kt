package com.iffy.mianshi.pattern.V单例

//线程不安全
class Boiler懒汉 {
    private constructor()

    //由于Kotlin中没有 static关键字，所以静态方法放在companion object{}中
    companion object {
        private var mInstance: Boiler懒汉? = null

        fun getInstance(): Boiler懒汉? {
            println("getInstance")
            if (mInstance == null) {
                mInstance = Boiler懒汉()
            }
            return mInstance

        }
    }


    fun b() {
        println(this)
    }

}


