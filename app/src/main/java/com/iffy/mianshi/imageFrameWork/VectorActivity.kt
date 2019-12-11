package com.iffy.mianshi.imageFrameWork

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R

class VectorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide)

        val imgA = findViewById<ImageView>(R.id.imageA)
        imgA.setImageResource(R.drawable.animated_vector)
        var animatedVectorDrawable = imgA.drawable as AnimatedVectorDrawable
        animatedVectorDrawable.start()


        val imgB = findViewById<ImageView>(R.id.imageB)
        imgB.isClickable = true
        imgB.setImageResource(R.drawable.ic_airline_seat_flat_angled_icon_selector)

        val imgC = findViewById<ImageView>(R.id.imageC)
        //在Android Studio 2.3之后，已经内置了对于WebP格式转换的功能，我们只需要在图片资源上点击右键，弹出菜单的最后一个选项：
        imgC.setImageResource(R.mipmap.ic_search)//这是一个webp格式的图片

    }
}