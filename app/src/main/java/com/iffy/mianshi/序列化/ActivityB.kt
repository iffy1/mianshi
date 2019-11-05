package com.iffy.mianshi.序列化

import android.os.Bundle
import android.util.Base64
import androidx.appcompat.app.AppCompatActivity
import leakcanary.AppWatcher
import org.jetbrains.anko.defaultSharedPreferences
import java.io.*


class ActivityB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("ActivityB onCreate getParcelableExtra")
        //从Intent中读取 Parcelable
        val duckParcelable = intent.getParcelableExtra<DuckParcelable>("对象P")
        println("ActivityB duckParcelable.name:${duckParcelable.name} 岁数是:${duckParcelable.age}")

        //从Intent中读取 Serializable
        val duckSerializable = intent.getSerializableExtra("对象S")
        duckSerializable as DuckSerializable
        println("ActivityB duckSerializable.name: ${duckSerializable.name}")

        //从文件读取Serializable
        //获取文件
        val file = File("${getExternalFilesDir("serializable")}/serializable.iffy")
        val fis = FileInputStream(file)
        val obj = ObjectInputStream(fis)
        val serializable = obj.readObject()
        serializable as DuckSerializable
        println("ActivityB!!! serializable.name: ${serializable.name}")


        //从文件读取Parcelable
        val data = ParcelUtil.readByteArrayFromFile(this)
        val duckParcelableFromFile = ParcelUtil.convertByteArrayToParcelable(data)
        println("ActivityB duckParcelableFromFile.name:${duckParcelableFromFile.name} 岁数是:${duckParcelableFromFile.age}")


        //从SP中读取Base64编码后的字符串
        val stringbase64 = defaultSharedPreferences.getString("spdata", "")
        //将Base64的字符串解码成二进制byteArray,再将ByteArray转化成Parcelable
        //Base64是一种能将任意Binary资料用64种字元组合成字串的方法，而这个Binary资料和字串资料彼此之间是可以互相转换的，十分方便。
        // 在实际应用上，Base64除了能将Binary资料可视化之外，也常用来表示字串加密过后的内容。如果要使用Java 程式语言来实作Base64的编码与解码功能
        val parcelableFromSP: DuckParcelable =
            ParcelUtil.convertByteArrayToParcelable(Base64.decode(stringbase64, Base64.DEFAULT))
        println("ActivityB duckParcelableFromSP.name:${parcelableFromSP.name} 岁数是:${parcelableFromSP.age}")


    }

    override fun onDestroy() {
        super.onDestroy()
        AppWatcher.objectWatcher.watch(this)
    }

}