package com.iffy.mianshi.firebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics

class MyFireBase:AppCompatActivity() {
    lateinit var fb:FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fb = FirebaseAnalytics.getInstance(this)

        var b = Bundle()
        b.putString(FirebaseAnalytics.Param.ITEM_ID,"idA")
        b.putString(FirebaseAnalytics.Param.ITEM_NAME,"iffy")
        fb.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT,b)
    }
}