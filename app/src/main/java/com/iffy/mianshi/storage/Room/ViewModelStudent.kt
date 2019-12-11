package com.iffy.mianshi.storage.Room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData



class ViewModelStudent(application: Application) : AndroidViewModel(application) {
    lateinit var studentLiveData: LiveData<List<StudentEntity>>// = MutableLiveData<List<StudentEntity>>()
    var db = MyDatabase.buildDatabase(application.applicationContext)

    fun getStudent() {
        println("getstudent b:$db")
        val data = db?.studentDao()?.getAll()
        if (data != null) {
            studentLiveData = data
        }
    }

    fun insertStudent(student: StudentEntity) {
        println("insert student")
        db?.studentDao()?.insert(student)
    }

    fun getStudentLivedata(): LiveData<List<StudentEntity>>? {
        return studentLiveData

    }


}