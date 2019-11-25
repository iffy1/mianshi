package com.iffy.mianshi.pattern.IVFactory.simpleFactory

//优点
//工厂类代替客户创建实例 实现客户与实例的解耦

//缺点
//违背开放（扩展）--关闭（修改）原则，当添加新产品时必须修改工厂类

class Factory{
    //伴生对象方法实现静态方法
    companion object{
        fun makeProduct(name:String):Product?{
            when(name){
                "A"->return ProductA()
                "B"->return ProductB()
                "C"->return ProductC()
            }
            println("没有这种产品")
            return null
        }
    }

}