package com.iffy.mianshi.intentservice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R
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
                "https://www.sony.com.cn/content/dam/sonystyle/common/homepage/category/Di/block_a7rm4_1909_600703.jpg"
            )
            startService(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onBusmsgAReceive(um: EventBusMsgA) {
        imageViewA.setImageBitmap(um.bitmap)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onBusmsgBReceive(um: EventBusMsgB) {
        imageViewB.setImageBitmap(um.bitmap)
    }
}