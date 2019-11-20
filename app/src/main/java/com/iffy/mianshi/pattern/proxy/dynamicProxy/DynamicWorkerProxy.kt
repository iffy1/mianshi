package com.iffy.mianshi.pattern.proxy.dynamicProxy

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

//可以同时代理多个接口
//这个例子里是workerAAblity workerBAblity
class DynamicWorkerProxy : InvocationHandler {
    // 声明代理对象
    // 作用：绑定关系，即关联到哪个接口（与具体的实现类绑定）的哪些方法将被调用时，执行invoke（）
    private var targetObject: Any? = null

    //通过 为Proy类指定类加载器对象 & 一组接口，从而创建动态代理类的字节码；再根据类字节码创建动态代理类
    fun getProxy(targetObject: Any?): Any {
        this.targetObject = targetObject
        // Proxy类 = 动态代理类的主类
        // Proxy.newProxyInstance（）作用：根据指定的类装载器、一组接口 & 调用处理器 生成动态代理类实例，并最终返回
        // 参数说明：
        // 参数1：指定产生代理对象的类加载器，需要将其指定为和目标对象同一个类加载器
        // 参数2：指定目标对象的实现接口
        // 即要给目标对象提供一组什么接口。若提供了一组接口给它，那么该代理对象就默认实现了该接口，这样就能调用这组接口中的方法
        // 参数3：指定InvocationHandler对象。即动态代理对象在调用方法时，会关联到哪个InvocationHandler对象
        return Proxy.newProxyInstance(
            this.targetObject!!::class.java.getClassLoader(),
            this.targetObject!!::class.java.getInterfaces(), this
        )
    }

    //关联的这个实现类的方法被调用时将被执行
    //InvocationHandler接口的方法，proxy表示代理，method表示原对象被调用的方法，args表示方法的参数
    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
        println("调用开始")
        args?.forEach {
            println(it)
        }
        // 通过Java反射机制调用目标对象方法
        return if (method != null) method.invoke(
            targetObject,
            *(args ?: arrayOfNulls<Any>(0))
        ) else throw NullPointerException("Expression 'method' must not be null")
    }

}