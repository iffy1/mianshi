package com.iffy.mianshi.callback

class WangB(internal var li: LiB) {

    fun askQuestion(num: Int) {
        Thread(Runnable {
            li.executeMessage(this, num)
        }).start()
        //问完不管得到答案与否，马上玩耍去了
        goPlay()
    }

    private fun goPlay() {
        println("问完问题,答案未知，玩去了...")
    }

     fun solve(result: String) {
        println("得到了答案 ： $result")
    }
}
