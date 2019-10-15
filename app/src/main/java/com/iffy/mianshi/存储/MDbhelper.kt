package com.iffy.mianshi.存储

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MDbhelper(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, DB_NAMR, factory, version) {
    companion object {
        val VERSION = 1.0
        val DB_NAMR = "mianshi.db"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            val sql =
                "create table mmm (id integer primary key autoincrement,topic varchar(100),content varchar(1000))"
            db.execSQL(sql)
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}