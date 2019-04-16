package com.example.kotlin_customprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import java.lang.UnsupportedOperationException

class CPTest : ContentProvider()
{
    override fun insert(uri: Uri, values: ContentValues?): Uri?
    {

    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int
    {
        var helper = DBHelper(context)
        var db = helper.writableDatabase
        return db.delete("Student", selection, selectionArgs)
    }

    override fun getType(uri: Uri): String?
    {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onCreate(): Boolean
    {
        return false
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor?
    {
        var helper = DBHelper(context)
        var db = helper.writableDatabase
        return db.query("Student", projection, selection, selectionArgs, null, null, sortOrder)
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int
    {
        var helper = DBHelper(context)
        var db = helper.writableDatabase
        return db.update("Student", values, selection, selectionArgs)
    }
}
