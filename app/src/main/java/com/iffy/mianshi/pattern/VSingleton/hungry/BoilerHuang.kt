package com.iffy.mianshi.pattern.VSingleton.hungry

//饿汉模式
//the object keyword.Object declaration's initialization is thread-safe.

object BoilerHuang {
    fun a() {
        println(this)
    }
}
