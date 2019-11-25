package com.iffy.mianshi.pattern.VSingleton.hungry

fun main() {
    BoilerHuang.a()
    //没有context不能使用
    //MySQLOpenHandler.databaseName

    val name = Name.getBikeName()
    println(name)
}