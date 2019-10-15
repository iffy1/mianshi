package com.iffy.mianshi.callback

//接口，誰設置誰/直接调用（选择在哪执行）谁給出結果，誰實現誰得到結果。
interface CallBack {
    fun solve(result: String)
}