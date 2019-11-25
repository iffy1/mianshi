package com.iffy.mianshi.pattern.VSingleton.lazy

class 懒汉demo private constructor() {
    companion object {
        @Volatile
        private var mInstance: 懒汉demo? = null
        val instance: 懒汉demo?
            get() {
                if (mInstance == null) {
                    synchronized(懒汉demo::class.java) {
                        if (mInstance == null) {
                            mInstance =
                                懒汉demo()
                        }
                    }
                }
                return mInstance
            }
    }
}