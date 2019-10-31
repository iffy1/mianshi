package com.iffy.mianshi.序列化

import android.os.Parcel
import android.os.Parcelable

//实现Parcelable步骤
//1）implements Parcelable
//2）重写writeToParcel方法，将你的对象序列化为一个Parcel对象，即：将类的数据写入外部提供的Parcel中，打包需要传递的数据到Parcel容器保存，以便从 Parcel容器获取数据
//3）重写describeContents方法，内容接口描述，默认返回0就可以
//4）实例化静态内部对象CREATOR实现接口Parcelable.Creator

class DuckB(var name: String) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()?:"dd") {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DuckB> {
        override fun createFromParcel(parcel: Parcel): DuckB {
            return DuckB(parcel)
        }

        override fun newArray(size: Int): Array<DuckB?> {
            return arrayOfNulls(size)
        }
    }


}