package com.iffy.async.handlerThread

import android.content.Context
import android.os.*
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.iffy.async.R
import com.iffy.async.handlerThread.HandlerThreadBActivity.Companion.TAG
import com.iffy.module_base.BaseActivity
import java.lang.ref.WeakReference

//Activity 和 handlerThread相互持有对方的handler 来互相通信
//handlerThread 的looper msgqueue队列 处理Activity发来的下载请求消息

class HandlerThreadBActivity : BaseActivity(), View.OnClickListener {
    override fun getContentId(): Int {
        return R.layout.activity_handler_thread_b
    }

    companion object {
        val STATUS_READY = 0
        val STATUS_DOWNLOADING = 1
        val STATUS_DONE = 2
        val STATUS_ERROR = 3

        val TASKA = 1
        val TASKB = 2
        val TASKC = 3
        val TASKD = 4
        val TASKE = 5

        val TAG = this.javaClass.simpleName
    }

    override fun onClick(p0: View?) {
        downloadThread.downloadHandler.sendEmptyMessage(TASKA)
        downloadThread.downloadHandler.sendEmptyMessage(TASKB)
        //msg queue可以为任务请求排序
        downloadThread.downloadHandler.sendEmptyMessageDelayed(TASKC,6000)
        downloadThread.downloadHandler.sendEmptyMessage(TASKD)
        downloadThread.downloadHandler.sendEmptyMessage(TASKE)
    }

    lateinit var uiHandler: UIHandler
    lateinit var downloadThread: DownloadThread

    lateinit var progressA: ProgressBar
    lateinit var progressB: ProgressBar
    lateinit var progressC: ProgressBar
    lateinit var progressD: ProgressBar
    lateinit var progressE: ProgressBar
    lateinit var blackBoard: TextView

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //UI init
        val startDownload: Button = findViewById(R.id.startDonwload)
        progressA = findViewById(R.id.contentLoadingProgressBarA)
        progressB = findViewById(R.id.contentLoadingProgressBarB)
        progressC = findViewById(R.id.contentLoadingProgressBarC)
        progressD = findViewById(R.id.contentLoadingProgressBarD)
        progressE = findViewById(R.id.contentLoadingProgressBarE)

        blackBoard = findViewById(R.id.tv_handler_blackboard)
        startDownload.setOnClickListener(this)
        uiHandler = UIHandler(this)

        initHandlerThread()
    }

    //初始化handlerThread
    fun initHandlerThread() {
        downloadThread = DownloadThread("iffy")
        downloadThread.uiHandler = uiHandler
        downloadThread.start()
    }

    //更新进度条UI
    @RequiresApi(Build.VERSION_CODES.N)
    fun updateProgress(progressid: Int, progressCount: Int) {
        when(progressid){
            TASKA->progressA.setProgress(progressCount, true)
            TASKB->progressB.setProgress(progressCount, true)
            TASKC->progressC.setProgress(progressCount, true)
            TASKD->progressD.setProgress(progressCount, true)
            TASKE->progressE.setProgress(progressCount, true)
        }
    }

    fun changeStatusDisplay(s: String) {
        blackBoard.text = s
    }

    //界面退出后 一定关闭thread
    override fun onDestroy() {
        super.onDestroy()
        downloadThread.quit()
    }
}

//主线程Handler
class UIHandler() : Handler() {
    lateinit var weakContext: WeakReference<Context>
    lateinit var activity: HandlerThreadBActivity

    constructor(c: Context) : this() {
        weakContext = WeakReference(c)
        activity = weakContext.get() as HandlerThreadBActivity
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        Log.e(TAG,"UIHandler handleMessage msg.what ${msg.what}")
        when (msg.what) {
            HandlerThreadBActivity.STATUS_READY -> activity.changeStatusDisplay("子线程已准备好")
            HandlerThreadBActivity.STATUS_DONE -> activity.changeStatusDisplay("下载完成")
            HandlerThreadBActivity.STATUS_DOWNLOADING -> {
                Log.e(TAG,"UIHandler 更新进度栏")
                activity.changeStatusDisplay("下载中。。。")
                activity.updateProgress(msg.arg1, msg.arg2)
            }
            HandlerThreadBActivity.STATUS_ERROR -> activity.changeStatusDisplay("下载出错")
        }
    }
}



