package com.iffy.mianshi.headfirst.I策略模式.weapon

class Troll:Character() {
    init {
        setWeaponBehavior(AxeBehavior())
    }
}