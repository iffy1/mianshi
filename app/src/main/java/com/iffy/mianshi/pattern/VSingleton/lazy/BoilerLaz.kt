package com.iffy.mianshi.pattern.VSingleton.lazy

//线程不安全
class BoilerLaz {
    //封闭构造函数
    private constructor() {
        //防止反射破坏
        if (mInstance != null) {
            throw  RuntimeException("Can not create instance in this class with Singleton")
        }
    }

    //由于Kotlin中没有 static关键字，所以静态方法放在companion object{}中
    companion object {
        private var mInstance: BoilerLaz? = null

        fun getInstance(): BoilerLaz? {
            println("getInstance")
            //双重检查
            //第一次判断是为了避免不必要的同步，第二次判断是确保在此之前没有其他线程进入到sychronized块创建了新实例
            if (mInstance == null) {
                synchronized(BoilerLaz::class.java) {
                    if (mInstance == null) {
                        mInstance = BoilerLaz()
                    }
                }
            }
            return mInstance

        }
    }


    fun b() {
        println(this)
    }

}


