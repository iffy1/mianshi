package com.iffy.mianshi.provider

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Room 三大组件里的Entity(对应数据库的表名)
@Entity(tableName = "person")
data class Person(
    //字段名
    @PrimaryKey(autoGenerate = true)
    var p_id: Int? = null,

    @ColumnInfo
    var name: String? = null,

    @ColumnInfo
    var age: Int? = 0
)