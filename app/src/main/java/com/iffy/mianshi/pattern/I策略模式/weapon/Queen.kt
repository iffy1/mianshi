package com.iffy.mianshi.pattern.I策略模式.weapon

class Queen:Character() {
    init {
        setWeaponBehavior(KnifeBehavior())
    }
}