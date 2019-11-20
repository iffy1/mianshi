package com.iffy.mianshi.pattern.proxy.staticProxy

//静态代理模式优点
//协调调用者和被调用者，降低了系统的耦合度
//代理对象作为客户端和目标对象之间的中介，起到了保护目标对象的作用

//缺点
//由于在客户端和真实主题之间增加了代理对象，因此会造成请求的处理速度变慢；
//实现代理模式需要额外的工作（有些代理模式的实现非常复杂），从而增加了系统实现的复杂度。

//每个代理类只能为一个接口服务，这样程序开发中必然会产生许多的代理类
//所以我们就会想办法可以通过一个代理类完成全部的代理功能，那么我们就需要用动态代理


fun main() {
    //调用者不应该知道具体是谁在干活，StaticWorkerAProxy知道谁去干

    //代理A负责WorkAAbility
    val proxyA = StaticWorkerAProxy()
    var result =proxyA.work(1,1)
    println("结果是$result")
    result =proxyA.work(2,2)
    println("结果是$result")

    //代理B负责WorkBAbility
    val proxyB = StaticWorkerBProxy()
    var res = proxyB.work(1,1,1)
    println("结果是$res")
    res = proxyB.work(2,2,2)
    println("结果是$res")

}