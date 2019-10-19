package com.iffy.mianshi.application


import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R


class ActivityApplicationB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application_b)
        title = "ActivityApplicationB"
        var blackboard = findViewById<TextView>(R.id.tv_applicationb_blackboard)
        var id = intent.getIntExtra(MyApplication.STUDENT_EXTRA,0)
        var student = (application as MyApplication).getStudent(id)
        blackboard.text = "ActivityA 放进去的学生名字是${student.name}"
    }
}