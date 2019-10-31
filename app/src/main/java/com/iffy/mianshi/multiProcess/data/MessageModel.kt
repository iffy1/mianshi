package com.iffy.mianshi.multiProcess.data


import android.os.Parcel
import android.os.Parcelable

//在AIDL中传递的对象，必须实现Parcelable序列化接口；
//在AIDL中传递的对象，需要在类文件相同路径下，创建同名、但是后缀为.aidl的文件，并在文件中使用parcelable关键字声明这个类；
//跟普通接口的区别：只能声明方法，不能声明变量；
//所有非基础数据类型参数都需要标出数据走向的方向标记。可以是 in、out 或 inout，基础数据类型默认只能是 in，不能是其他方向。


//实现Parcelable步骤
//1）implements Parcelable
//2）重写writeToParcel方法，将你的对象序列化为一个Parcel对象，即：将类的数据写入外部提供的Parcel中，打包需要传递的数据到Parcel容器保存，以便从 Parcel容器获取数据
//3）重写describeContents方法，内容接口描述，默认返回0就可以
//4）实例化静态内部对象CREATOR实现接口Parcelable.Creator

//MessageModel.kt -> 消息实体类，由客户端传(IPCActivity)递到服务端(MessageIPCService)，实现了Parcelable序列化
class MessageModel(var from: String?, var to: String?, var content: String?) : Parcelable {
//    private var from: String? = null
//    private var to: String? = null
//    private var content: String? = null

    constructor(parcel: Parcel) : this(
        parcel?.readString(),
        parcel?.readString(),
        parcel?.readString()
    ) {
//        from = parcel?.readString()
//        to = parcel?.readString()
//        content = parcel?.readString()
    }


    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(from)
        dest?.writeString(to)
        dest?.writeString(content)

    }

    override fun describeContents(): Int {
        return 0
    }


    companion object CREATOR : Parcelable.Creator<MessageModel> {
        override fun createFromParcel(parcel: Parcel): MessageModel {
            return MessageModel(parcel)
        }

        override fun newArray(size: Int): Array<MessageModel?> {
            return arrayOfNulls(size)
        }
    }

}