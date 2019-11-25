package com.iffy.mianshi.pattern.facade.system

class Facade {
    private var lightSystem = LightSystem()
    private var musicSystem = MusicSystem()

    fun backHome() {
        lightSystem.turnOnLight()
        musicSystem.playMusic()
    }

    fun leaveHome() {
        lightSystem.turnOffLight()
        musicSystem.stopMusic()
    }

    //使用内部类 禁止外部调用
    private inner class LightSystem {
        fun turnOnLight() {
            println("开灯")
        }

        fun turnOffLight() {
            println("关灯")
        }
    }

    //使用内部类 禁止外部调用
    private inner class MusicSystem {
        fun playMusic() {
            println("开始播放音乐")
        }

        fun stopMusic() {
            println("停止播放音乐")
        }
    }
}