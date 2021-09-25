package com.example.newsapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COLUMN_1 + " INTEGER PRIMARY KEY," + COLUMN_2 + " INTEGER, " + COLUMN_3 + " TEXT," + COLUMN_4 + " TEXT" + ")")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addData(articleId: Int, title: String?, url: String?): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_2, articleId)
        values.put(COLUMN_3, title)
        values.put(COLUMN_4, url)
        val result = db.insert(TABLE_NAME, null, values)
        return result != -1L
    }

    fun viewData(): Cursor {
        val db = this.writableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
    }

    companion object {
        const val DATABASE_NAME = "news.db"
        const val TABLE_NAME = "news_table"
        const val COLUMN_1 = "ID"
        const val COLUMN_2 = "ARTICLE_ID"
        const val COLUMN_3 = "TITLE"
        const val COLUMN_4 = "URL"
    }
}