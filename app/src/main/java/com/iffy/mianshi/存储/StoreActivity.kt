package com.iffy.mianshi.存储

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.iffy.mianshi.R
import org.json.JSONArray
import java.io.File


class StoreActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.store_to_db -> MDbhelper(this, null, null, 1).readableDatabase
            R.id.store_to_sp -> {
                var sp = getSharedPreferences("mianshi", Context.MODE_PRIVATE)
                sp.edit().putString("key", "mianshi").commit()
            }
            R.id.store_to_file -> {
                var path = this.filesDir.path + "/mianshi.txtt"
                var f = File(path)
                f.createNewFile()
            }
            R.id.store_to_cp -> {
                var cv = ContentValues()
                cv.put("ket", "miashi")
                contentResolver.insert(ContactsContract.Data.CONTENT_URI, cv)
            }
            R.id.store_to_network -> {
                val str = "[{name:'a',value:'aa'},{name:'b',value:'bb'},{name:'c',value:'cc'},{name:'d',value:'dd'}]"
                val json = JSONArray(str)
                for (i in 0..json.length() - 1) {
                    println(json.get(i).toString())
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)

        findViewById<Button>(R.id.store_to_db).setOnClickListener(this)
        findViewById<Button>(R.id.store_to_sp).setOnClickListener(this)
        findViewById<Button>(R.id.store_to_file).setOnClickListener(this)
        findViewById<Button>(R.id.store_to_cp).setOnClickListener(this)

        //通过json 保存到网络
        findViewById<Button>(R.id.store_to_network).setOnClickListener(this)
    }
}