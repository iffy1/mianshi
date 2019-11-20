package com.iffy.mianshi.pattern.proxy.dynamicProxy

import com.iffy.mianshi.pattern.proxy.staticProxy.WorkAAbility
import com.iffy.mianshi.pattern.proxy.staticProxy.WorkBAbility
import com.iffy.mianshi.pattern.proxy.staticProxy.WorkerA
import com.iffy.mianshi.pattern.proxy.staticProxy.WorkerB


//在目标对象较多的情况下，若采用静态代理，则会出现 静态代理对象量多、代码量大，从而导致代码复杂的问题
//解决方案:
//采用动态代理模式

//设计动态代理类（DynamicProxy）时，不需要显式实现与目标对象类（RealSubject）相同的接口，而是将这种实现推迟到程序运行时由 JVM来实现
fun main() {
    // 1. 创建 目标类 对象
    val workerA = WorkerA.getInstance()

    // 2. 创建动态代理类 & 对象：通过调用处理器类对象newProxyInstance（）
    // 传入上述目标类对象
    val proxyA = DynamicWorkerProxy().getProxy(workerA)

    // 3. 通过调用动态代理对象方法从而调用目标对象方法
    val resultA = (proxyA as WorkAAbility).work(1, 2)
    //var resultA = proxyA.work(1, 2)
    println("DynamicWorkerProxy动态代理A结果为$resultA")

    var proxyB = DynamicWorkerProxy().getProxy(WorkerB.getInstance())
    proxyB as WorkBAbility
    var result = proxyB.work(1, 2, 3)
    println("DynamicWorkerProxy动态代理B结果为$result")


}