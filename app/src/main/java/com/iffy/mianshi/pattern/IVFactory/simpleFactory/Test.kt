package com.iffy.mianshi.pattern.IVFactory.simpleFactory

fun main() {

    try {
        val product = Factory.makeProduct("A")
        product!!.show()
    } catch (e: NullPointerException) {
        println("没生产出这种产品")
    }

    try {
        val product = Factory.makeProduct("B")
        product!!.show()
    } catch (e: NullPointerException) {
        println("没生产出这种产品")
    }

    try {
        val product = Factory.makeProduct("C")
        product!!.show()
    } catch (e: NullPointerException) {
        println("没生产出这种产品")
    }

    try {
        val product = Factory.makeProduct("D")
        product!!.show()
    } catch (e: NullPointerException) {
        println("没生产出这种产品")
    }


}