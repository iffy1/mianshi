package com.iffy.mianshi.viewOptimiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R


class ViewOptActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_opti)
        val mData = ArrayList<ShakeMaps.dataObj>()
        var obj: ShakeMaps.dataObj
        for (i in 0..999) {
            obj = ShakeMaps.dataObj()
            obj.setX(i)
            obj.setY1((Math.random() * -60).toInt() + 30)
            obj.setY2(0)
            mData.add(obj)
        }
        (findViewById(R.id.shakemaps) as ShakeMaps).setmData(mData, 40, -40)
    }
}