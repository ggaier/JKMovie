package com.github.ggaier.jkmovie.data.vo

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
data class Genre(val id: String, val name: String) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Genre> = object : Parcelable.Creator<Genre> {
            override fun createFromParcel(source: Parcel): Genre = Genre(source)
            override fun newArray(size: Int): Array<Genre?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(name)
    }
}