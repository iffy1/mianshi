package com.iffy.mianshi.pattern.VSingleton.hungry


object Name : Bike("iffy") {

}

open class Bike(var s: String) {
    fun getBikeName(): String {
        return s
    }
}

