package com.iffy.mianshi.pattern.IStrategy.weapon

 open class Character {
    lateinit var mWeaponBehavior:WeaponBehavior
    fun setWeaponBehavior(weaponBehavior:WeaponBehavior){
        mWeaponBehavior = weaponBehavior
    }

     fun fight(){
         mWeaponBehavior.useWeapon()
     }

}