package com.iffy.mianshi.pattern.IStrategy.weapon

class King:Character() {
    init {
        setWeaponBehavior(SwordBehavior())
    }
}