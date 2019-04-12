package com.example.kotlin_parcelable

import android.os.Parcel
import android.os.Parcelable

class TestClass : Parcleable
{
    var age : Int = 0
    var name : String? = null
    companion object
    {
        @JvmField
        val CREATOR: Parcelable.Creator<TestClass> = object : Parcelable.Creator<TestClass>
        {
            override fun createFromParcel(source: Parcel?): TestClass
            {
                val test = TestClass() //데이터 복구
                test.age = source?.readInt()!!
                test.name = source?.readString()
                return test
            }

            override fun newArray(size : Int) : Array<TestClass?> =
                    arrayOfNulls<TestClass>(size)
        }
    }
    override fun describeContents(): Int = 0
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        //dest에 순서대로 데이터를 입력한다.
        //주의할점은 객체를 생성(재구성)할때에는 입력한 순서대로 입력해야한다.
        dest?.writeInt(age)
        dest?.writeString(name)
    }

}