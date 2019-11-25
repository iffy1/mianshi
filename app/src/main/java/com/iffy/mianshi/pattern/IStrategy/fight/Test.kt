package com.iffy.mianshi.pattern.IStrategy.fight

//策略模式
// 优点
//
//    策略类之间可以自由切换
//    由于策略类都实现同一个接口，所以使它们之间可以自由切换。
//    易于扩展
//    增加一个新的策略只需要添加一个具体的策略类即可，基本不需要改变原有的代码，符合“开闭原则“
//    避免使用多重条件选择语句（if else），充分体现面向对象设计思想。
//
//3.2 缺点
//
//    客户端必须知道所有的策略类，并自行决定使用哪一个策略类。
//    策略模式将造成产生很多策略类，可以通过使用享元模式在一定程度上减少对象的数量。
//

fun main() {
    var fighter = Fighter()
    fighter.strategy = FightArmStrategy()
    fighter.fight()
}