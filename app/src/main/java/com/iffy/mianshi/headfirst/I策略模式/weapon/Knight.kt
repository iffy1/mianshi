package com.iffy.mianshi.headfirst.I策略模式.weapon

class Knight:Character() {
    init {
        setWeaponBehavior(BowAndArrowBehavior())
    }
}