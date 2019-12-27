package com.iffy.mianshi.temp.niming



    fun main() {
        var msgListener = object : IMsgListener {
            override fun onGetMessage(s: String) {
                println(s)
            }
        }
        var b = BBB()
        b.setMListener(msgListener)
        b.getMSG()
    }




class BBB {
    lateinit var listener: IMsgListener

    fun setMListener(listener: IMsgListener) {
        this.listener = listener
    }

    fun getMSG() {
        var msg = "haha"
        listener.onGetMessage(msg)

    }

}

