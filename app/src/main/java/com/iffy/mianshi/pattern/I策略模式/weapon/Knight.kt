package com.iffy.mianshi.pattern.I策略模式.weapon

class Knight:Character() {
    init {
        setWeaponBehavior(BowAndArrowBehavior())
    }
}