package com.iffy.mianshi.headfirst.I策略模式.weapon

class King:Character() {
    init {
        setWeaponBehavior(SwordBehavior())
    }
}