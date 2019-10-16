package com.iffy.mianshi.headfirst.XII复合模式

fun main(){
    var groupA = Flock()
    groupA.add(Duck("A"))
    groupA.add(Duck("B"))
    groupA.add(Duck("C"))
    groupA.quack()

    println("--------------------------")

    var groupB = Flock()
    groupB.add(Duck("E"))
    groupB.add(Duck("F"))
    groupB.add(Duck("G"))

    groupA.add(groupB)

    groupA.quack()



}