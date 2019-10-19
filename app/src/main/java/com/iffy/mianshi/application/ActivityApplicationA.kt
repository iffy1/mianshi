package com.iffy.mianshi.application

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R

class ActivityApplicationA : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application_a)
        title = "ActivityApplicationA"

        val id = 1
        val storedstudent = (application as MyApplication).getStudent(id)
        title = "学生名字是${storedstudent.name}"

        val student = Student(id, "iffy")
        //不能用MyApplication(),会产生多个不同实例
        (application as MyApplication).insertStudent(id, student)


        val btn = findViewById<Button>(R.id.btn_application_thread)
        btn.setOnClickListener {
            val intent = Intent()
            intent.putExtra(MyApplication.STUDENT_EXTRA, id)
            intent.setClass(this, ActivityApplicationB::class.java)
            startActivity(intent)
        }
    }
}