package com.iffy.mianshi.hotfix

import android.content.Context
import android.widget.Toast
//Kotlin app\build\tmp\kotlin-classes\debug\com\iffy\mianshi\hotfix\BugClass.class
//这个class 转成dex后 有内部错误 不能使用

class BugClass {
    companion object {
        fun dairYouTryThis(c: Context): Int {
            return 1 / 0
        }
    }
}

//编译完会出现在app\build\tmp\kotlin-classes\debug\com\iffy\mianshi\hotfix\BugClass.class
//把他用dx命令打包 放入hotfix目录或者服务器上供应用下载
//1.根据bug的实际情况修改代码即可
//2.使用Android Studio的Rebuild Project功能将代码进行编译，然后从build目录下找到对应的class文件
//3.将修复好的class文件复制到其他地方，例如桌面上的dex文件夹中。需要注意的是，在复制这个class文件时，需要把它所在的完整包目录一起复制
//4.将class文件打包成dex文件，要将class文件打包成dex文件，就需要用到dx指令，dx就在Android SDK的build-tools
//C:\Users\28849026\AppData\Local\Android\Sdk\build-tools\29.0.2\dx
//C:\Users\28849026\AppData\Local\Android\Sdk\build-tools\29.0.2\dx --dex --output=C:\Users\28849026\Desktop\dex\classes2.dex C:\Users\28849026\Desktop\dex
//class BugClass {
//    companion object {
//        fun dairYouTryThis(c: Context): Int {
//            Toast.makeText(c,"我修好了",Toast.LENGTH_LONG).show()
//            return 0
//        }
//    }
//}