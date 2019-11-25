package com.iffy.mianshi.pattern.IVFactory.factory

import com.iffy.mianshi.pattern.IVFactory.factory.factory.BikeFactory
import com.iffy.mianshi.pattern.IVFactory.factory.factory.CarFactory

//工厂模型可以解决简单工厂模式的违背“开放扩展关闭修改规则”
//简单工厂模式的缺点问题（工厂一旦需要生产新产品就需要修改工厂类的方法逻辑，违背了“开放 - 关闭原则）
//之所以可以解决简单工厂的问题，是因为工厂方法模式把具体产品的创建推迟到工厂类的子类（具体工厂）中，
// 此时工厂类不再负责所有产品的创建，而只是给出具体工厂必须实现的接口，
// 这样工厂方法模式在添加新产品的时候就不修改工厂类逻辑而是添加新的工厂子类，符合开放封闭原则，克服了简单工厂模式中缺点


fun main() {
    val bikeFactory = BikeFactory()
    val bike = bikeFactory.makeProduct()
    bike.show()

    val carFactory = CarFactory()
    val car = carFactory.makeProduct()
    car.show()
}