package com.iffy.mianshi.storage.Room

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [StudentEntity::class], version = 2)
abstract class MyDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao

    companion object {
        private var instance: MyDatabase? = null
        private val LOCK = Any()

        fun buildDatabase(context: Context): MyDatabase? {
            if (instance == null) {
                synchronized(LOCK) {
                    if (instance == null) {
                        println("start build db")
                        instance =
                            Room.databaseBuilder(
                                context.applicationContext,
                                MyDatabase::class.java,
                                "student.db"
                            )
                                .addMigrations(FirstMigration())//升级使用
                                .build()
                    }
                }
            }
            return instance
        }
    }


    class FirstMigration : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            println("FirstMigration")
            database.execSQL("Create TABLE 'B'('ID' INTEGER,'TYPE' TEXT)")
        }

    }

}