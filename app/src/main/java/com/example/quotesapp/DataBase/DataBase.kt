package com.example.kotlin10.Screen.shared_screen.controller

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteOpenHelper
import kotlin.Throws
import android.database.sqlite.SQLiteDatabase
import android.content.Context
import android.os.Build
import com.example.quotesapp.ModelData.QuotesMD
import com.example.quotesapp.ModelData.QuotesReadMD
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.lang.Error

class DataBase @SuppressLint("SdCardPath") constructor(context: Context?) :
    SQLiteOpenHelper(context, "Quotes.db", null, 1) {
    var context: Context?
    var DB_NAME = "Quotes.db"
    var path: String? = null
    private fun checkDataBase(): Boolean {
        val dbFile = File(path + DB_NAME)
        return dbFile.exists()
    }

    private fun copyDataBase() {
        if (!checkDataBase()) {
            this.readableDatabase
            close()
            try {
                copyDBFile()
            } catch (mIOException: IOException) {
                throw Error("ErrorCopyingDataBase")
            }
        }
    }

    @Throws(IOException::class)
    private fun copyDBFile() {
        val mInput = context!!.assets.open(DB_NAME)
        val mOutput: OutputStream = FileOutputStream(path + DB_NAME)
        val mBuffer = ByteArray(1024)
        var mLength: Int
        while (mInput.read(mBuffer).also { mLength = it } > 0) mOutput.write(mBuffer, 0, mLength)
        mOutput.flush()
        mOutput.close()
        mInput.close()
    }

    override fun onCreate(db: SQLiteDatabase) {}
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}

    init {
        path = if (Build.VERSION.SDK_INT >= 17) {
            context!!.applicationInfo.dataDir + "/databases/"
        } else {
            "/data/data/" + context!!.packageName + "/databases/"
        }
        this.context = context
        this.context = context
        copyDataBase()
        this.readableDatabase
    }

    @SuppressLint("Range")
    fun readCategory(): ArrayList<QuotesMD> {
        val db = readableDatabase
        val query = "SELECT * FROM categories"
        val list = arrayListOf<QuotesMD>()
        val cursor = db.rawQuery(query,null)

        if(cursor.moveToFirst())
        {
            do {
                var id = cursor.getString(cursor.getColumnIndex("_id"))
                var name = cursor.getString(cursor.getColumnIndex("name"))
                val quotesMD = QuotesMD(id,name)
                list.add(quotesMD)

            }while (cursor.moveToNext())
        }
        return list
    }

    @SuppressLint("Range")
    fun readQuotes(id: String): ArrayList<QuotesReadMD> {
        val db1 = readableDatabase
        val query = "SELECT * FROM quotes WHERE category_id = $id"
        val list2 = arrayListOf<QuotesReadMD>()
        val cursor = db1.rawQuery(query,null)

        if(cursor.moveToFirst())
        {
            do {
                val Id = cursor.getString(cursor.getColumnIndex("_id"))
                val Aid = cursor.getString(cursor.getColumnIndex("author_id"))
                val Cid = cursor.getString(cursor.getColumnIndex("category_id"))
                val Body = cursor.getString(cursor.getColumnIndex("body"))
                val Aname = cursor.getString(cursor.getColumnIndex("author_name"))
                val Cname = cursor.getString(cursor.getColumnIndex("category_name"))
                val Aimg = cursor.getString(cursor.getColumnIndex("author_image"))
                val Tags = cursor.getString(cursor.getColumnIndex("tags"))
                val Related = cursor.getString(cursor.getColumnIndex("related"))
                val Oindex = cursor.getString(cursor.getColumnIndex("order_index"))
                val Viewed = cursor.getString(cursor.getColumnIndex("viewed"))

                val quotesReadMD = QuotesReadMD(Id,Aid,Cid,Body,Aname,Cname,Aimg,Tags,Related,Oindex,Viewed)
                list2.add(quotesReadMD)
            }while (cursor.moveToNext())
        }
        return list2
    }
}