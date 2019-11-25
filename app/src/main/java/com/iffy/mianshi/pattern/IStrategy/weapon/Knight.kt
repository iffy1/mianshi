package com.iffy.mianshi.pattern.IStrategy.weapon

class Knight:Character() {
    init {
        setWeaponBehavior(BowAndArrowBehavior())
    }
}