package com.iffy.mianshi.headfirst.VI命令模式

import com.iffy.mianshi.headfirst.VI命令模式.devices.Light
import com.iffy.mianshi.headfirst.VI命令模式.devices.LightCommand
import com.iffy.mianshi.headfirst.VI命令模式.devices.TV
import com.iffy.mianshi.headfirst.VI命令模式.devices.TVCommand


fun main(){
    var ctr = Controller()
    ctr.addCommand(LightCommand(Light()))
    ctr.addCommand(TVCommand(TV()))

    ctr.turnOn(1)
    ctr.turnOff(1)

}