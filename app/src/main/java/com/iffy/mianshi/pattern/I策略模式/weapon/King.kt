package com.iffy.mianshi.pattern.I策略模式.weapon

class King:Character() {
    init {
        setWeaponBehavior(SwordBehavior())
    }
}