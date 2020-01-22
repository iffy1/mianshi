package com.iffy.async

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.iffy.async.Coroutine.CoroutineMainScopeActivity
import com.iffy.async.handler.HandlerActivity
import com.iffy.async.handler.HandlerBetweenMainAndSubActivity
import com.iffy.async.handlerThread.HandlerThreadActivity
import com.iffy.async.handlerThread.HandlerThreadBActivity
import com.iffy.async.rxjava.RxjavaActivity
import com.iffy.async.synchronize.SynchronizedActivity
import com.iffy.module_base.BaseActivity

class MainActivity : BaseActivity() {
    override fun getContentId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun goToHandlerThread(v: View) {
        val intent = Intent()
        intent.setClass(this, HandlerThreadBActivity::class.java)
        startActivity(intent)
    }

    fun goToHandlerThreadSimple(v: View) {
        val intent = Intent()
        intent.setClass(this, HandlerThreadActivity::class.java)
        startActivity(intent)
    }


    fun goToHandlerActivity(v: View) {
        val intent = Intent()
        intent.setClass(this, HandlerActivity::class.java)
        startActivity(intent)
    }

    fun goToHandlerActivityBetweenMainAndSub(v: View) {
        val intent = Intent()
        intent.setClass(this, HandlerBetweenMainAndSubActivity::class.java)
        startActivity(intent)
    }
    fun goToCoroutineMainScopeActivity(v: View) {
        val intent = Intent()
        intent.setClass(this, CoroutineMainScopeActivity::class.java)
        startActivity(intent)
    }
    fun goToSynchronizedActivity(v: View) {
        val intent = Intent()
        intent.setClass(this, SynchronizedActivity::class.java)
        startActivity(intent)
    }
    fun goToAsyncActivity(v: View) {
        val intent = Intent()
        intent.setClass(this, AsyncActivity::class.java)
        startActivity(intent)
    }
    fun goToRxjavaActivity(v: View) {
        val intent = Intent()
        intent.setClass(this, RxjavaActivity::class.java)
        startActivity(intent)
    }

    fun goToProduceCustomerActivity(v: View) {
        val intent = Intent()
        intent.setClass(this, ProduceCustomerActivity::class.java)
        startActivity(intent)
    }






}
