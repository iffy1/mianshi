package com.iffy.mianshi.headfirst.XII复合模式


class Flock : QuackAble {
    var list:ArrayList<QuackAble>
    init {
        list = ArrayList<QuackAble>()
    }

    fun add(s: QuackAble) {
        list.add(s)
    }

    override fun quack() {
        list.forEach {
            println(it.quack())
        }
    }
}