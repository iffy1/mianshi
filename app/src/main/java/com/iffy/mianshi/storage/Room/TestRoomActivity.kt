package com.iffy.mianshi.storage.Room

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iffy.mianshi.R


class TestRoomActivity : AppCompatActivity() {
    lateinit var studentVm: ViewModelStudent
    lateinit var pictureVm: ViewModelPicture
    lateinit var board: TextView
    lateinit var listView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        board = findViewById(R.id.board)
        listView = findViewById(R.id.img_list)

        prepareStudent()
        insertStudent()

        prepareRecycle()
    }

    fun prepareRecycle() {
        val adapter = Adapter()
        listView.adapter = adapter
        val lm = LinearLayoutManager(this)
        lm.orientation = RecyclerView.VERTICAL
        listView.layoutManager = lm


        pictureVm = ViewModelProviders.of(this)[ViewModelPicture::class.java]
        pictureVm.retriveImageData()
        pictureVm.getImageData().observe(this, object : Observer<ArrayList<String>> {
            override fun onChanged(t: ArrayList<String>?) {
                if (t != null) {
                    adapter.data = t
                    //adapter.notifyDataSetChanged()
                }
            }
        })
    }

    fun prepareStudent() {
        studentVm = ViewModelProviders.of(this)[ViewModelStudent::class.java]
        studentVm.getStudent()
        studentVm.getStudentLivedata()
            ?.observe(this, object : Observer<List<StudentEntity>> {
                override fun onChanged(t: List<StudentEntity>?) {
                    println("onChanged")
                    var result = ""
                    t?.forEach {
                        result += it.name + "\n"
                    }
                    board.text = result
                }
            })
    }

    fun insertStudent() {
        Thread(object : Runnable {
            override fun run() {
                studentVm.insertStudent(StudentEntity(null, "zhang san"))
                Thread.sleep(1000)
                studentVm.insertStudent(StudentEntity(null, "li si"))
                Thread.sleep(1000)
                studentVm.insertStudent(StudentEntity(null, "wang wu"))
                Thread.sleep(1000)
                studentVm.insertStudent(StudentEntity(null, "deng liu"))
                Thread.sleep(1000)
                studentVm.insertStudent(StudentEntity(null, "zhao qi"))
                Thread.sleep(1000)
                studentVm.insertStudent(StudentEntity(null, "dong ba"))
                Thread.sleep(1000)
                studentVm.insertStudent(StudentEntity(null, "cai jiu"))
            }
        }).start()
    }
}