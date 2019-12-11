package com.iffy.mianshi.storage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R

//1. SharedPreferences不支持进程同步
//2.考虑用ContentProvider来实现SharedPreferences的进程同步.
//Google认为多个进程读同一个文件都是不安全的，不建议这么做，推荐使用ContentProivder来处理多进程间的文件共享
//实际上就是一条原则：确保一个文件只有一个进程在读写操作

//ContentProvider+数据库，个人感觉还是太重了
//-这个类继承于ContentProvider，让google内部帮我们实现多进程同步机制
//-希望使用的人（程序员），还是当以往使用SharedPreferences的感觉去操作


//1.使用MODE_MULTI_PROCESS时，不要保存SharedPreference变量，必须每次都从context.getSharedPreferences 获取。
// 如果你图方便使用变量存了下来，那么无法触发reload，有可能两个进程数据不同步。
//2.前面提到过，load数据是耗时的，并且其他操作会等待该锁。
// 这意味着很多时候获取SharedPreference数据都不得不从文件再读一遍，大大降低了内存缓存的作用。文件读写耗时也影响了性能。
//3.修改数据时得用commit，保证修改时写入了文件，这样其他进程才能通过文件大小或修改时间感知到。
//
//综上，无论怎么说，MODE_MULTI_PROCESS都很糟糕，避免使用就对了。

//跨进程的SharedPreference——Tray
class MySharedPreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sharedpreference)

    }
}