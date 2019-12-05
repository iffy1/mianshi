package com.iffy.mianshi.image

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.TransitionOptions
import com.iffy.mianshi.R

class GlideActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide)
        val img = findViewById<ImageView>(R.id.imageA)
        Glide
            .with(this)
            .load("https://www.2008php.com/2013_Website_appreciate/2013-07-15/20130715214140vLfjDvLfjD.jpg")
            .transition(GenericTransitionOptions.with(R.anim.item_alpha_in))//动画效果
            .placeholder(R.mipmap.loading)//默认图片
            .error(R.mipmap.error)//出错后的图片
            .into(img)
    }
}