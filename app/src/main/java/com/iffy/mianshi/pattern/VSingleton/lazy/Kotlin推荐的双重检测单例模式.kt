package com.iffy.mianshi.pattern.VSingleton.lazy

class kotlin推荐的双重检测单例模式 constructor() {
    companion object {
        val mInstance: kotlin推荐的双重检测单例模式 by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            kotlin推荐的双重检测单例模式()
        }
    }
    var b = println(this)
}