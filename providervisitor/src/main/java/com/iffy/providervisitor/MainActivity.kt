package com.iffy.providervisitor



import android.content.ContentValues
import android.database.ContentObserver
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    val PERSON_AUTHORITY = "com.iffy.mianshi.provider.PersonContentProvider"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider)

        val insertBtn = findViewById<Button>(R.id.insert_btn)
        insertBtn.setOnClickListener {
            println("Visitor 插入联系人")
            val contentvalue = ContentValues()
            contentvalue.put("name", "王五")
            contentvalue.put("age", "66")
            this.contentResolver.insert(
                Uri.parse("content://${PERSON_AUTHORITY}/person"),
                contentvalue
            )
        }

        val deleteBtn = findViewById<Button>(R.id.delete_btn)
        deleteBtn.setOnClickListener {
            println("Visitor 删除联系人")
            //随机删除一个人
            contentResolver.delete(
                Uri.parse(
                    "content://${PERSON_AUTHORITY}/person/${Random.nextInt(
                        10
                    )}"
                ), null, null
            )
        }

        // 参数需要一个 Hanlder，因为 ContentObserver 内部使用了一个实现 Runnable 接口的内部类 NotificationRunnable，
        // 需要通过 Handler 去做比如 UI 变化
        val ob = MyObserver(Handler())
        this.contentResolver.registerContentObserver(
            Uri.parse("content://${PERSON_AUTHORITY}/person"),
            true,
            ob
        )

        this.contentResolver.registerContentObserver(
            Uri.parse("content://${PERSON_AUTHORITY}/person/#"),
            true,
            ob
        )


    }


    class MyObserver(var han: Handler) : ContentObserver(han) {
        override fun onChange(selfChange: Boolean) {
            super.onChange(selfChange)
            var msg = han.obtainMessage()
            msg.arg1 = 0
            han.sendMessage(msg)
        }

        override fun onChange(selfChange: Boolean, uri: Uri?) {
            println("Thrad: ${Thread.currentThread().name}  providervisitor MyObserver onChange:有事情发生 $uri")
            super.onChange(selfChange, uri)
        }
    }
}

