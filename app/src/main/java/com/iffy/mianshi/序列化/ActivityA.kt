package com.iffy.mianshi.序列化

import android.content.Intent
import android.os.Bundle
import android.util.Base64
import androidx.appcompat.app.AppCompatActivity
import leakcanary.AppWatcher
import org.jetbrains.anko.defaultSharedPreferences
import java.io.*

//序列化的对象可以用于
//1进程间传递
//sharedpreference存储

class ActivityA : AppCompatActivity() {

    lateinit var duckParcelableInnerProcess: DuckParcelable
    lateinit var duckParcelableFiel: DuckParcelable
    lateinit var duckParcelableSP: DuckParcelable

    lateinit var duckSerializable: DuckSerializable
    lateinit var duckSerializableFile: DuckSerializable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        duckParcelableInnerProcess = DuckParcelable("DuckParcelable进程间传递", "一岁")
        duckParcelableFiel = DuckParcelable("DuckParcelable文件传递", "两岁")
        duckParcelableSP = DuckParcelable("DuckParcelable SP传递", "三岁")

        duckSerializable = DuckSerializable("DuckSerializable intent传递")
        duckSerializableFile = DuckSerializable("DuckSerializable 文件传递")

        //Serializable存储到本地
        //获取文件
        val file = File("${getExternalFilesDir("serializable")}/serializable.iffy")
        val fos = FileOutputStream(file)
        val obo = ObjectOutputStream(fos)
        obo.writeObject(duckSerializableFile)
        obo.close()


        //Parcelable 持久化本地存储
        val byteArray = ParcelUtil.convertParcelableToByteArray(duckParcelableFiel)
        println(byteArray)
        ParcelUtil.saveByteArrayToFile(byteArray, this)

        //Parcelable转换成Base64 编码的String 写入SP
        //Base64是一种能将任意Binary资料用64种字元组合成字串的方法，而这个Binary资料和字串资料彼此之间是可以互相转换的，十分方便。
        // 在实际应用上，Base64除了能将Binary资料可视化之外，也常用来表示字串加密过后的内容。如果要使用Java 程式语言来实作Base64的编码与解码功能
        val string_data = String(
            Base64.encode(
                ParcelUtil.convertParcelableToByteArray(duckParcelableSP),
                Base64.DEFAULT
            )
        )
        defaultSharedPreferences.edit().putString("spdata", string_data).commit()
        //DuckA不支持序列化 所以不能传递
        //i.putExtra("对象A", DuckA("small duck"))

        //进程间传递
        val i = Intent()
        println("ActivityA 创建序列化对象")
        i.putExtra("对象P", duckParcelableInnerProcess)
        i.putExtra("对象S", duckSerializable)
        i.setClass(this, ActivityB::class.java)
        println("ActivityA 将对象传递给 ActivityB")
        startActivity(i)

    }

    override fun onDestroy() {
        super.onDestroy()
        AppWatcher.objectWatcher.watch(this)
    }

}