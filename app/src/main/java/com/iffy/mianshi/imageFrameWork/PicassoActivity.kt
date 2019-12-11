package com.iffy.mianshi.imageFrameWork

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R
import com.squareup.picasso.Picasso

class PicassoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide)

        val imgA = findViewById<ImageView>(R.id.imageA)
        Picasso.get()
            .load("https://raw.githubusercontent.com/square/picasso/master/website/static/sample.png")
            .placeholder(R.mipmap.loading)//默认显示的图片
            .error(R.mipmap.error)//错误时候显示的图片
            .into(imgA)


    }
}