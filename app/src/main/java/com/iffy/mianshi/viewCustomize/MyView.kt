package com.iffy.mianshi.viewCustomize

import android.content.Context
import android.util.AttributeSet
import android.view.View

class MyView : View {

    //当我们在代码中使用new 对象的方式创建一个自定View的时候，只能使用一个参数的构造函数。
    constructor(context: Context) : this(context, null){
        println("我是第一个构造函数")
    }

    //当我们在布局文件里面使用自定义View的时候，调用的是两个参数的构造函数
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0){
        println("我是第二个构造函数")
    }

    //无论是在代码中new一个CustomView实例，还是在布局文件中使用，
    // 最终都会调用三个参数的构造函数，所以我们可以在三个参数的构造函数里面，做一些初始化操作和获取自定义属性。
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int):super(context, attrs, defStyleAttr){
        println("我是第三个构造函数")
    }


}


