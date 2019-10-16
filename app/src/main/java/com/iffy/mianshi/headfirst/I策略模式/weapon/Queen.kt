package com.iffy.mianshi.headfirst.I策略模式.weapon

class Queen:Character() {
    init {
        setWeaponBehavior(KnifeBehavior())
    }
}