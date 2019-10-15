package com.iffy.mianshi.callback

class Wang(internal var li: Li) : CallBack {

    fun askQuestion(num: Int) {
        Thread(Runnable {
            li.executeMessage(this@Wang, num)
        }).start()
        //问完不管得到答案与否，马上玩耍去了
        goPlay()
    }

    private fun goPlay() {
        println("问完问题,答案未知，玩去了...")
    }

    override fun solve(result: String) {
        println("得到了答案 ： $result")
    }
}
