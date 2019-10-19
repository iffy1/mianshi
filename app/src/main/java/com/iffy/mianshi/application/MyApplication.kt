package com.iffy.mianshi.application

import android.app.Application
import android.util.SparseArray

//Application对象的生命周期是整个程序中最长的，它的生命周期就等于这个程序的生命周期。
// 因为它是全局的单例的，所以在不同的Activity,Service中获得的Application对象都是同一个对象。
// 所以可以通过Application来进行一些，数据传递，数据共享,数据缓存等操作。

//1、通过Application传递数据 Application数据共享
//2、Application数据缓存
//易导致的错误
//使用Application如果保存了一些不该保存的对象很容易导致内存泄漏。
//如果在Application的onCreate中执行比较耗时的操作，将直接影响程序的启动时间。
//一些清理工作不能依靠onTerminate完成，因为android会尽量让你的程序一直运行，所以很有可能onTerminate()方法 不会被调用
class MyApplication : Application() {
    //基本思路是这样的。在Application中创建一个HashMap ，以字符串为索引，Object为value这样我们的HashMap就可 以存储任何类型的对象了。
    //在Activity A中把需要传递的对象放入这个HashMap，然后通过Intent或者其它途经再把这索引的字符串传递给Activity B ,
    // Activity B 就可以根据这个字符串在HashMap中取出这个对象了。只要再向下转个型 ，就实现对象的传递。
    //1、通过Application传递数据 Application数据共享
    var studentListShareData = SparseArray<Student>()

    // 我一般会习惯在Application中建立两个HashMap一个用于数据的传递，一个用于缓存一些数据。
    //比如有一个Activity需要从网站获取一些数据，获取完之后我们就可以把这个数据cache到Application当中，
    //当页面设置到其它Activity再回来的时候，就可以直接使用缓存好的数据了。但如果需要cache一些大量的数据，
    //最好是cache一些 (软引用)SoftReference ，并把这些数据cache到本地rom上或者sd卡上。
    //如果在application中的缓存不存在，从本地缓存查找，如果本地缓存的数据也不存在再从网络上获取。
    //2、Application数据缓存
    var catcheData = SparseArray<Student>()

    companion object {
        val STUDENT_EXTRA = "student"
    }

    //程序创建的时候执行
    override fun onCreate() {
        super.onCreate()
        println("Application onCreate")
    }

    //当终止应用程序对象时调用，不保证一定被调用，
    override fun onTerminate() {
        super.onTerminate()
        println("Application onTerminate")
    }

    //低内存的时候执行
    override fun onLowMemory() {
        super.onLowMemory()
    }

    fun insertStudent(id: Int, student: Student) {
        studentListShareData.append(id, student)
        println("插入学生${student.name}")
    }

    fun getStudent(id: Int): Student {
        if (studentListShareData.get(id) != null) {
            return studentListShareData?.get(id)
        }
        return Student(-1, "No student")

    }

}