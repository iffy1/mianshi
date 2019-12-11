package com.iffy.mianshi.storage.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class StudentEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var name: String
)