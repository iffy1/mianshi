package com.iffy.mianshi.headfirst.I策略模式.weapon

 open class Character {
    lateinit var mWeaponBehavior:WeaponBehavior
    fun setWeaponBehavior(weaponBehavior:WeaponBehavior){
        mWeaponBehavior = weaponBehavior
    }

     fun fight(){
         mWeaponBehavior.useWeapon()
     }

}