package com.iffy.mianshi.pattern.I策略模式.weapon

class Troll:Character() {
    init {
        setWeaponBehavior(AxeBehavior())
    }
}