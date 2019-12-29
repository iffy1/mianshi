package com.iffy.mianshi.storage.provider

import android.content.Context
import androidx.room.*
import java.lang.Exception

@Database(entities = [Person::class], version = 1)
abstract class PersonDatabase : RoomDatabase() {

    abstract fun getPersonDao(): PersonDao

    companion object {
        @Volatile
        private var instance: PersonDatabase? = null

        fun getDB(context: Context?): PersonDatabase? {
            println("获取DB getDB")
            if(context == null){
                throw(Exception("context 不能为空，DB 创建失败"))
            }
            if (instance == null) {
                println("新建DB")
                instance = Room.databaseBuilder(
                    context,
                    PersonDatabase::class.java,
                    "person.db"
                ).allowMainThreadQueries().build()
            }
            return instance
        }
    }

}