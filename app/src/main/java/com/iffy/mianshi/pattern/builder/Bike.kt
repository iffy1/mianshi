package com.iffy.mianshi.pattern.builder

class Bike {
    private var description = "这辆自行车有"

    fun add(s: String) {
        description += "$s ,"
    }

    fun ShowInstruction(){
        println(description)
    }
}