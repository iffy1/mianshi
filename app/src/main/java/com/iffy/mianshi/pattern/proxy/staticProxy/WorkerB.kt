package com.iffy.mianshi.pattern.proxy.staticProxy

class WorkerB :WorkBAbility{
    //覆盖掉默认的构造函数
    private constructor()

    companion object{
        private var mInstance:WorkerB?= null
        fun getInstance():WorkerB?{
            if(mInstance == null){
                mInstance = WorkerB()
            }
            return mInstance
        }
    }
    override fun work(a:Int,b:Int,c:Int):Int {
        println("WorkerB干活去喽")
        return a+b+c
    }

}