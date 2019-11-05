package com.iffy.mianshi.序列化

import android.os.Parcel
import android.os.Parcelable
import java.io.FileDescriptor

//Parcelable是Android为我们提供的序列化的接口,Parcelable相对于Serializable的使用相对复杂一些,
// 但Parcelable的效率相对Serializable也高很多,这一直是Google工程师引以为傲的,
// 有时间的可以看一下Parcelable和Serializable的效率对比 Parcelable vs Serializable 号称快10倍的效率


//实现Parcelable步骤
//1）implements Parcelable
//2）重写writeToParcel方法，将你的对象序列化为一个Parcel对象，
// 即：将类的数据写入外部提供的Parcel中，打包需要传递的数据到Parcel容器保存，以便从 Parcel容器获取数据
//3）重写describeContents方法，内容接口描述，默认返回0就可以
//4）实例化静态内部对象CREATOR实现接口Parcelable.Creator

//Parcelable中的三大过程介绍(序列化,反序列化,描述)
//一序列化 通过序列化在进程中传递对象， ！！！只有Serializable才能实现序列化后进行网络传输和本地存储
//二反序列化

class DuckParcelable : Parcelable {
    var name: String?
    var age:String?

    constructor(name: String,age:String) {
        println("Parcelable 调用构造函数用户生成新的对象")
        this.name = name
        this.age = age
    }



    //序列化 将对象写入parcel包 顺序不能错
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        println("Parcelable 序列化 writeToParcel 将对象写入parcel包")
        parcel.writeString(name)
        parcel.writeString(age)
        FileDescriptor()
        parcel.readFileDescriptor()
    }

    //describeContents 描述
    //其中describeContents就是负责文件描述.通过源码的描述可以看出,
    //只针对一些特殊的需要描述信息的对象,需要返回1,其他情况返回0就可以
    override fun describeContents(): Int {
        return 0
    }

    //用于反序列化，把parcel包还原成对象 顺序不能错!!!
    constructor(parcel: Parcel) {
        //反序列化
        println("Parcelable 反序列化 把parcel包还原成对象")
        this.name = parcel.readString()
        this.age = parcel.readString()
    }

    //反序列化
    companion object CREATOR : Parcelable.Creator<DuckParcelable> {
        override fun createFromParcel(parcel: Parcel): DuckParcelable {
            //调用构造 把parcel还原为对象
            return DuckParcelable(parcel)
        }
        //创建指定长度的原始对象数组
        override fun newArray(size: Int): Array<DuckParcelable?> {
            return arrayOfNulls(size)
        }
    }


}