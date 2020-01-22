package com.iffy.async.handlerThread

import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.util.Log
//下载线程，looper会给发来的多个任务放入队列
class DownloadThread(taskName: String) : HandlerThread(taskName), Handler.Callback {
    val TAG = this.javaClass.simpleName
    lateinit var downloadHandler:Handler
    lateinit var uiHandler: UIHandler

    //处理msg queue（activity）发来的消息
    override fun handleMessage(p0: Message): Boolean {
        Log.e(TAG, "p0.what is ${p0.what}")
        for (i in 0..100) {
            Thread.sleep(50)
            val msg = Message.obtain()
            msg.what = HandlerThreadBActivity.STATUS_DOWNLOADING
            //对应的progressBar
            msg.arg1 = p0.what
            //对应的进度
            msg.arg2 = i

            //通知UI更新
            uiHandler.sendMessage(msg)
            Log.e(TAG, "DownloadThread 进度条${msg.arg1} 进度${msg.arg2} thread: ${Thread.currentThread().name}")
        }
        //下载结束后更新状态为下载完成
        uiHandler.sendEmptyMessage(HandlerThreadBActivity.STATUS_DONE)
        return true
    }


    override fun onLooperPrepared() {
        super.onLooperPrepared()
        uiHandler.sendEmptyMessage(HandlerThreadBActivity.STATUS_READY)
        //handler要在onLooperPrepared里面创建，不要在定义变量时候创建，不然会绑定到主线程
        downloadHandler = Handler(this)
    }

}