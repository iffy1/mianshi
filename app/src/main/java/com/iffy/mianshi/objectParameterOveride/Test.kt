package com.iffy.mianshi.objectParameterOveride

fun main() {
    val b = B()
    println(b.num)

    val c: A = B()
    c as A
    println(c.num)
}

internal class B : A() {
    override var num = 10
}

internal open class A {
    open var num = 5
}