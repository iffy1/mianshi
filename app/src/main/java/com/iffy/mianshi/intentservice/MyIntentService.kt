package com.iffy.mianshi.intentservice

import android.app.IntentService
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import org.greenrobot.eventbus.EventBus
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL


//处理异步请求 & 实现多线程
//线程任务 需 按顺序、在后台执行
//最常见的场景：离线下载
//不符合多个数据同时请求的场景：所有的任务都在同一个Thread looper里执行

//注意事项1：工作任务队列 = 顺序执行
//注意事项2：不建议通过 bindService() 启动 IntentService 原因： 在IntentService中，onBind()`默认返回null
class MyIntentService(name: String) : IntentService(name) {
    //防止manifest报错
    constructor() : this("XXX")

    override fun onCreate() {
        super.onCreate()
        println("MyIntentService onCreate Thread:${Thread.currentThread().getName()}")
    }

    //子線程里面運行
    override fun onHandleIntent(intent: Intent?) {
        println("MyIntentService  onHandleIntent Thread:${Thread.currentThread().getName()}")
        var btnIden = intent?.getIntExtra(MyIntentServiceActivity.BTN_IDENTIFY, 0)
        var address = intent?.getStringExtra(MyIntentServiceActivity.IMGURL)
        var connect = URL(address).openConnection() as HttpURLConnection
        //连接类型
        connect.requestMethod = "GET"
        connect.connect()
        //连接成功
        if (connect.responseCode == HttpURLConnection.HTTP_OK) {
            //转化为bitmap
            var bitmap = BitmapFactory.decodeStream(connect.inputStream)
            println(bitmap.allocationByteCount / 1024)
            //andriond建议用自己应用的空间/storage/emulated/0/Android/data/com.iffy.mianshi/files/mianshi.jpg
            var file = File(getExternalFilesDir(null), "mianshi.jpg")
            //创建FileOutputStream流以写入数据到File对象所代表的文件
            var fos = FileOutputStream(file)
            //保存bitmap到文件用到的是图片压缩方法
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            //一般主要用在IO中，即清空缓冲区数据，就是说你用读写流的时候，其实数据是先被读到了内存中，然后用数据写到文件中，
            // 当你数据读完的时候不代表你的数据已经写完了，因为还有一部分有可能会留在内存这个缓冲区中。
            // 这时候如果你调用了 close()方法关闭了读写流，那么这部分数据就会丢失，所以应该在关闭读写流之前先flush()，先清空数据。
            fos.flush()
            fos.close()

            when (btnIden) {
                0 -> {
                    EventBus.getDefault().post(EventBusMsgA(bitmap))
                    EventBus.getDefault().post(EventBusMsgB(bitmap))
                }
                1 -> EventBus.getDefault().post(EventBusMsgA(bitmap))
                2 -> EventBus.getDefault().post(EventBusMsgB(bitmap))
            }

        }

        //利用eventbus回傳
    }

    override fun onDestroy() {
        super.onDestroy()
        println("MyIntentService onDestroy Thread:${Thread.currentThread().getName()}")
    }

}