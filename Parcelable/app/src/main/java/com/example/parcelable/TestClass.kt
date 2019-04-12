package com.example.parcelable

import android.os.Parcel
import android.os.Parcelable

class TestClass : Parcelable
{
    var age : Int = 0
    var name : String? = null
    var address : String? = null
    var phone_number : String? = null

    companion object
    {
        @JvmField
        val CREATOR : Parcelable.Creator<TestClass> = object : Parcelable.Creator<TestClass>
        {
            override fun createFromParcel(source: Parcel?): TestClass
            {
                val t = TestClass()
                t.age = source?.readInt()!!
                t.name = source?.readString()
                t.address = source?.readString()
                t.phone_number = source?.readString()
                return t
            }

            override fun newArray(size: Int): Array<TestClass?> =
                arrayOfNulls<TestClass>(size)
        }
    }

    override fun describeContents(): Int = 0
    override fun writeToParcel(dest: Parcel?, flags: Int)
    {
        dest?.writeInt(age)
        dest?.writeString(name)
        dest?.writeString(address)
        dest?.writeString(phone_number)
    }
}