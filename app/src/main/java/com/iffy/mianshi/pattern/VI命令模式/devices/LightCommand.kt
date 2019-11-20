package com.iffy.mianshi.pattern.VI命令模式.devices

class LightCommand(var light:Light):NormalCommand {
    override fun turnOn() {
        light.turnOnLight()
    }

    override fun turnOff() {
        light.turnOffLight()
    }

}