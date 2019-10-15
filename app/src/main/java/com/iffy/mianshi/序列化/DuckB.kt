package com.iffy.mianshi.序列化

import android.os.Parcel
import android.os.Parcelable

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