package com.iffy.mianshi.pattern.builder.adroidCode

class ZixingcheBuilder {
    private var whealNumber: Int = 0
    private var ligthNumber: Int = 0
    private var seatNumber: Int = 0

    fun addWheal(i: Int):ZixingcheBuilder {
        whealNumber = i
        return this
    }

    fun addLight(i: Int):ZixingcheBuilder  {
        ligthNumber = i
        return this
    }

    fun addSeats(i: Int):ZixingcheBuilder  {
        seatNumber = i
        return this
    }

    fun build(): Bike {
        return Bike(whealNumber, ligthNumber, seatNumber)
    }

}

class Bike(private var w: Int,private var l: Int,private var s: Int) {
    fun show() {
        println("这个自行车有$w 个轮子，$l 个灯，$s 个座位")
    }

}