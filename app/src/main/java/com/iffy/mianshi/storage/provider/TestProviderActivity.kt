package com.iffy.mianshi.storage.provider

import android.content.ContentValues
import android.database.ContentObserver
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R

import leakcanary.AppWatcher
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import kotlin.random.Random



class TestProviderActivity : AppCompatActivity() {
    companion object {
        val UPDATE_DATA = 1
    }

    lateinit var blackboard: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider)

        val insertBtn = findViewById<Button>(R.id.insert_btn)
        insertBtn.setOnClickListener {
            println("TestProviderActivity 插入一个联系人")
            val contentvalue = ContentValues()
            contentvalue.put("name", "王五")
            contentvalue.put("age", "66")
            this.contentResolver.insert(
                Uri.parse("content://${IffyContentProvider.PERSON_AUTHORITY}/person"),
                contentvalue
            )
        }

        val deleteBtn = findViewById<Button>(R.id.delete_btn)
        deleteBtn.setOnClickListener {
            println("TestProviderActivity 删除一个联系人")
            //随机删除一个人
            contentResolver.delete(
                Uri.parse(
                    "content://${IffyContentProvider.PERSON_AUTHORITY}/person/${Random.nextInt(
                        10
                    )}"
                ), null, null
            )
        }

        // 参数需要一个 Hanlder，因为 ContentObserver 内部使用了一个实现 Runnable 接口的内部类 NotificationRunnable，
        // 需要通过 Handler 去做比如 UI 变化
        val ob = MyObserver(Handler {
            if (it.arg1 == UPDATE_DATA) {
                println("Handler 收到更新blackboard请求")
                displayDBdata()
            }
            false
        })

        this.contentResolver.registerContentObserver(
            Uri.parse("content://${IffyContentProvider.PERSON_AUTHORITY}/person"),
            true,
            ob
        )

        this.contentResolver.registerContentObserver(
            Uri.parse("content://${IffyContentProvider.PERSON_AUTHORITY}/person/#"),
            true,
            ob
        )

        blackboard = findViewById<TextView>(R.id.tv_blackboard)
        displayDBdata()

        val displayButn = findViewById<Button>(R.id.display_btn)
        displayButn.setOnClickListener {
            displayDBdata()
        }
    }

    fun displayDBdata() {
        println("do async准备")
        doAsync {
            println("do async开始 A")
            var result = contentResolver.query(
                Uri.parse("content://${IffyContentProvider.PERSON_AUTHORITY}/person"),
                null,
                null,
                null
            )
            println("do async开始 B")
            result as Cursor
            uiThread {
                blackboard.text = ""
                while (result.moveToNext()) {
                    blackboard.text =
                        "${blackboard.text} ${result.getInt(result.getColumnIndex("p_id"))} " +
                                "${result.getString(result.getColumnIndex("name"))} " +
                                "${result.getInt(result.getColumnIndex("age"))} \n"
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        AppWatcher.objectWatcher.watch(this)
    }


    class MyObserver(var han: Handler) : ContentObserver(han) {
        var lastTimeofCallOnchangeA = 0L
        var lastTimeofUpdateOnchangA = 0L
        var threshold_time: Long = 3000

        var lastTimeofCallOnchangeB = 0L
        var lastTimeofUpdateOnchangB = 0L
        //这块一个更新会调用两次或更多 需要用时间滤掉
        override fun onChange(selfChange: Boolean) {
            super.onChange(selfChange)
            lastTimeofCallOnchangeA = System.currentTimeMillis()
            if (lastTimeofCallOnchangeA - lastTimeofUpdateOnchangA > threshold_time) {
                println(println("Thrad: ${Thread.currentThread().name}  TestProviderActivity MyObserver onChange"))
                var msg = han.obtainMessage()
                msg.arg1 = UPDATE_DATA
                han.sendMessage(msg)
                lastTimeofUpdateOnchangA = System.currentTimeMillis()
            }
        }


        override fun onChange(selfChange: Boolean, uri: Uri?) {
            super.onChange(selfChange, uri)
            lastTimeofCallOnchangeB = System.currentTimeMillis()
            if (lastTimeofCallOnchangeB - lastTimeofCallOnchangeB > threshold_time) {
                println("Thrad: ${Thread.currentThread().name}  TestProviderActivity MyObserver onChange:有事情发生 $uri selfChange: $selfChange")
                lastTimeofCallOnchangeB = System.currentTimeMillis()
            }
        }
    }
}
