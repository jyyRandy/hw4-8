package com.bytedance.homework.homework6


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class MyDBHelper(private val context: Context, name: String, version: Int): SQLiteOpenHelper(context, name, null, version) {

    private val createTodoList = "create table todolist(" +
            "id integer primary key autoincrement," +
            "title text)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createTodoList)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}