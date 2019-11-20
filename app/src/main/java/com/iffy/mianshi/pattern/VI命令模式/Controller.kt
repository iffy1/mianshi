package com.iffy.mianshi.pattern.VI命令模式

import com.iffy.mianshi.pattern.VI命令模式.devices.NormalCommand

class Controller (){
    var commandList = ArrayList<NormalCommand>()

    fun addCommand(cmd:NormalCommand){
        commandList.add(cmd)
    }

    fun turnOn(i: Int){
        commandList[i].turnOn()
    }

    fun turnOff(i: Int){
        commandList[i].turnOff()
    }

}
