package com.iffy.mianshi.pattern.proxy.staticProxy

//每个代理类只能为一个接口服务，这样程序开发中必然会产生许多的代理类
//所以我们就会想办法可以通过一个代理类完成全部的代理功能，那么我们就需要用动态代理

class StaticWorkerBProxy : WorkBAbility {
    override fun work(a: Int, b: Int, c: Int): Int? {
        var worker = WorkerB.getInstance()
        println("WorkerBProxy让$worker 去干活")
        return worker?.work(a, b, c)
    }
}