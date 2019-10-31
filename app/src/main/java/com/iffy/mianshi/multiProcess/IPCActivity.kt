package com.iffy.mianshi.multiProcess

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.multiProcess.MessageSender
import com.iffy.mianshi.R
import com.iffy.mianshi.multiProcess.data.MessageModel

//整个app都在一个进程有什么弊端？
//在Android中，虚拟机分配给各个进程的运行内存是有限制值的（这个值可以是32M，48M，64M等，根据机型而定），
// 试想一下，如果在app中，增加了一个很常用的图片选择模块用于上传图片或者头像，加载大量Bitmap会使app的内存占用迅速增加，
// 如果你还把查看过的图片缓存在了内存中，那么OOM的风险将会大大增加，如果此时还需要使用WebView加载一波网页，我就问你怕不怕！
//
//微信，微博等主流app是如何解决这些问题的？
//微信移动开发团队在 《Android内存优化杂谈》 一文中就说到：“对于webview，图库等，由于存在内存系统泄露或者占用内存过多的问题，
// 我们可以采用单独的进程。微信当前也会把它们放在单独的tools进程中”。
class IPCActivity : AppCompatActivity() {


    //不能用 lateinit var messageSender: IBinder? lateinit 和Nullable冲突
    var messageSender: MessageSender? = null
    var messageReceiver: MessageReceiver? = null
    lateinit var serviceConnection: ServiceConnection
    lateinit var deadthRecipient: IBinder.DeathRecipient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ipc_service)
        messageReceiver = object : MessageReceiver.Stub() {
            override fun onMessageReceived(receivedMessage: MessageModel?) {
                println("IPCActivity -> 服务器来消息了")
                runOnUiThread {
                    Toast.makeText(
                        this@IPCActivity,
                        "服务器来消息了: ${receivedMessage?.from}${receivedMessage?.to}${receivedMessage?.content}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        /**
        Binder可能会意外死忙（比如Service Crash），Client监听到Binder死忙后可以进行重连服务等操作
        Binder死亡代理
        我们都知道，在和service进行交互时，service返回一个Binder对象。Binder是工作在service端，如果，由于某种原因，服务端出现故障而死亡，
        那么该返回的Binder对象也将消失，这时，如果我们在客户端在使用Binder对象进行某些函数调用将会出现错误。
        为了避免该情况的发生，我们可以为Binder对象设置死亡代理。当出现和服务端连接发生故障时，系统将自动调用死亡代理函数binderDied（）。
         **/
        deadthRecipient = IBinder.DeathRecipient {
            println("DeathRecipient  binderDied()")
            //unlinkToDeath（）：将设置的死亡代理标志清除。
            //unlinkToDeath -> Binder死亡的情况下，解除该代理。
            messageSender?.asBinder()?.unlinkToDeath(deadthRecipient, 0)
            messageSender = null
            //重新bind
            startBind()
        }

        val btn = findViewById<Button>(R.id.button_remote_service)
        btn.setOnClickListener {
            println("点击按钮开始绑定")
            startBind()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //解除消息监听接口
        messageSender?.unregisterReceiveListener(messageReceiver)
        //解绑service
        if (serviceConnection != null) {
            unbindService(serviceConnection)
        }
    }


    fun startBind() {
        serviceConnection = object : ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName?) {
                println("onServiceDisconnected")
            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                println("onServiceConnected")
                //设置Binder死亡监听  DeathRecipient 对象
                service?.linkToDeath(deadthRecipient, 0)

                //使用asInterface方法取得AIDL对应的操作接口
                messageSender = MessageSender.Stub.asInterface(service)


                // 获取当前进程 id 并取得进程名
                var pid = android.os.Process.myPid()
                println("Actitvity 发送消息 进程为 $pid")

                //调用远程Service的sendMessage方法，并传递消息实体对象给MessageIPCService
                messageSender?.sendMessage(MessageModel("我", "你", "他"))

                messageSender?.registerReceiveListener(messageReceiver)

            }
        }
        var i = Intent()
        i.setClass(this, MessageIPCService::class.java)
        bindService(i, serviceConnection, Context.BIND_AUTO_CREATE)
    }
}