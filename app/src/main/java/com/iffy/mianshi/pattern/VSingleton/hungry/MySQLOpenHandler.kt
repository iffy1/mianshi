package com.iffy.mianshi.pattern.VSingleton.hungry

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//作为例子，让我们来声明一个实现数据库帮助的对象：
object MySQLOpenHandler : SQLiteOpenHelper(null, "MyDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

}