package com.iffy.mianshi.pattern.proxy.staticProxy

class WorkerA :WorkAAbility{
    //覆盖掉默认的构造函数
    private constructor()

    companion object{
        private var mInstance:WorkerA?= null
        fun getInstance():WorkerA?{
            if(mInstance == null){
                mInstance = WorkerA()
            }
            return mInstance
        }
    }
    override fun work(a:Int,b:Int):Int {
        println("WorkerA干活去喽")
        return a+b
    }

}