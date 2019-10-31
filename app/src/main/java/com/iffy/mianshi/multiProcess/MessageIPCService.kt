package com.iffy.mianshi.multiProcess

import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.IBinder
import android.os.Parcel
import com.iffy.mianshi.multiProcess.data.MessageModel
import android.os.RemoteCallbackList



//整个app都在一个进程有什么弊端？
//在Android中，虚拟机分配给各个进程的运行内存是有限制值的（这个值可以是32M，48M，64M等，根据机型而定），
// 试想一下，如果在app中，增加了一个很常用的图片选择模块用于上传图片或者头像，加载大量Bitmap会使app的内存占用迅速增加，
// 如果你还把查看过的图片缓存在了内存中，那么OOM的风险将会大大增加，如果此时还需要使用WebView加载一波网页，我就问你怕不怕！
//
//多进程app可以在系统中申请多份内存，但应合理使用，建议把一些高消耗但不常用的模块放到独立的进程，不使用的进程可及时手动关闭；
//多进程应用，应用的Application将会被创建多次；


//微信，微博等主流app是如何解决这些问题的？
//微信移动开发团队在 《Android内存优化杂谈》 一文中就说到：“对于webview，图库等，由于存在内存系统泄露或者占用内存过多的问题，
// 我们可以采用单独的进程。微信当前也会把它们放在单独的tools进程中”。
class MessageIPCService : Service() {
    //RemoteCallbackList专门用来管理多进程回调接口
    //存储注册监听客户端集合
    //为什么要用RemoteCallbackList，普通ArrayList不行吗？当然不行，不然干嘛又整一个RemoteCallbackList 🙃，
    // registerReceiveListener 和 unregisterReceiveListener在客户端传输过来的对象，经过Binder处理，
    // 在服务端接收到的时候其实是一个新的对象，这样导致在 unregisterReceiveListener 的时候，
    // 普通的ArrayList是无法找到在 registerReceiveListener 时候添加到List的那个对象的，
    // 但是它们底层使用的Binder对象是同一个，RemoteCallbackList利用这个特性做到了可以找到同一个对象，
    // 这样我们就可以顺利反注册客户端传递过来的接口对象了。RemoteCallbackList在客户端进程终止后，
    // 它能自动移除客户端所注册的listener，它内部还实现了线程同步，所以我们在注册和反注册都不需要考虑线程同步，的确是个666的类。
    // （至于使用ArrayList的幺蛾子现象，大家可以自己试试，篇幅问题，这里就不演示了）
    private val listenerList = RemoteCallbackList<MessageReceiver>()

    override fun onBind(intent: Intent?): IBinder? {
        println("MessageIPCService onBind()开始FakeTCPTask")
        //权限检测 有两种方法 一种是检查permission,另外一种是在ontransact里面检查包名
        if (checkCallingOrSelfPermission("com.iffy.mianshi.multiProcess.permission.REMOTE_SERVICE_PERMISSION") == PackageManager.PERMISSION_DENIED) {
            println("没有权限 禁止绑定")
            return null
        }

        Thread(FakeTCPTask()).start()
        return messageSender
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        println("MessageIPCService onStartCommand")
        return START_STICKY
    }

    private var messageSender = object : MessageSender.Stub() {
        //在服务端中增加MessageSender的注册接口的方法；
        override fun registerReceiveListener(messageReceiver: MessageReceiver?) {
            listenerList.register(messageReceiver)
        }

        //在服务端中增加MessageSender的反注册接口的方法；
        override fun unregisterReceiveListener(messageReceiver: MessageReceiver?) {
            listenerList.unregister(messageReceiver)
        }

        override fun sendMessage(messageModel: MessageModel?) {
            // 获取当前进程 id 并取得进程名
            var pid = android.os.Process.myPid()
            println("MessageIPCService 接收消息 进程为 $pid")
            println("MessageIPCService sendMessage${messageModel?.from}${messageModel?.to}${messageModel?.content}")
        }

        override fun onTransact(code: Int, data: Parcel, reply: Parcel?, flags: Int): Boolean {
            println("onTransact")
            /**
             * 包名验证方式
             */
            var packageName: String? = null
            val packages = packageManager.getPackagesForUid(getCallingUid())
            if (packages != null && packages.isNotEmpty()) {
                packageName = packages[0]
            }
            if (packageName == null || !packageName.startsWith("com.iffy.mianshi")) {
                println("onTransact拒绝调用：$packageName")
                return false
            }
            println("onTransact包名验证通过 可以调用：$packageName")
            return super.onTransact(code, data, reply, flags)
        }
    }

    //模拟网络消息
    inner class FakeTCPTask : Runnable {
        override fun run() {
            while (true) {
                Thread.sleep(5000)

                for (i in 0 until listenerList.beginBroadcast()) {
                    println("模拟网络发送消息")
                    listenerList.getBroadcastItem(i)
                        .onMessageReceived(MessageModel("网上来", "消息", "了"))
                }
                //不然会抛异常beginBroadcast() called while already in a broadcast
                listenerList.finishBroadcast()

            }
        }

    }

}

