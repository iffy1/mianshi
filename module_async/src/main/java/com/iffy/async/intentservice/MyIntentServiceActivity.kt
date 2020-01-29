package com.iffy.async.intentservice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.iffy.async.R
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MyIntentServiceActivity : AppCompatActivity() {
    lateinit var imageViewA: ImageView
    lateinit var imageViewB: ImageView

    companion object {
        val IMGURL = "url"
        val BTN_IDENTIFY = "BTN"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_service)
        imageViewA = findViewById(R.id.imageView_intentA)
        imageViewB = findViewById(R.id.imageView_intentB)
        //扫描这个界面所有的带有@subscrib注解的方法并且以eventType为Key保存到map里面
        EventBus.getDefault().register(this)

        val btnA = findViewById<Button>(R.id.button_internt_service_A)
        btnA.setOnClickListener {
            var intent = Intent(this, MyIntentService::class.java)
            intent.putExtra(BTN_IDENTIFY, 1)
            intent.putExtra(
                IMGURL,
                "https://www.sony.com.cn/content/dam/sonystyle/common/homepage/category/pa/block_wf_1000xm3_0723_600703.jpg"
            )
            startService(intent)
        }
        val btnB = findViewById<Button>(R.id.button_internt_service_B)
        btnB.setOnClickListener {
            var intent = Intent(this, MyIntentService::class.java)
            intent.putExtra(BTN_IDENTIFY, 2)
            intent.putExtra(
                IMGURL,
                "https://www.sony.com.cn/content/dam/sonystyle/common/homepage/category/pa/block_srs_xb12_2001_600295.jpg"
            )
            startService(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        //取消监听
        EventBus.getDefault().unregister(this)
    }

    //监听回调 接收发布者的消息，这里的注解是在EventBus.getDefault().register(this)时候，通过反射查找这个activity
    //里面带有@Subscrib注解的所有方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onBusmsgAReceive(um: EventBusMsgA) {
        imageViewA.setImageBitmap(um.bitmap)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onBusmsgBReceive(um: EventBusMsgB) {
        imageViewB.setImageBitmap(um.bitmap)
    }
}