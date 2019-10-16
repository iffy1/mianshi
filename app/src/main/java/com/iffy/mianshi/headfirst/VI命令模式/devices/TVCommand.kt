package com.iffy.mianshi.headfirst.VI命令模式.devices

class TVCommand(var tv: TV) : NormalCommand {
    override fun turnOn() {
        tv.turnOnTV()
    }

    override fun turnOff() {
        tv.turnOffTV()
    }
}