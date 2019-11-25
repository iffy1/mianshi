package com.iffy.mianshi.pattern.IStrategy.weapon

class Queen:Character() {
    init {
        setWeaponBehavior(KnifeBehavior())
    }
}