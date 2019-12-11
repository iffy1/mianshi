package com.iffy.mianshi.storage.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDao {
    @Query("Select * from student")
    fun getAll(): LiveData<List<StudentEntity>>

    @Insert
    fun insert(student: StudentEntity)

}