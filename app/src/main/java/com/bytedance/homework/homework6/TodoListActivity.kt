package com.bytedance.homework.homework6

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.homework.R

class TodoListActivity : AppCompatActivity() {

    private var todoListView: RecyclerView? = null
    private val dbHelper = MyDBHelper(this, "TodoList.db", 1)
    private var db: SQLiteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_list)
        todoListView = findViewById(R.id.rv_todoList)
        todoListView?.layoutManager = LinearLayoutManager(this)
        db = dbHelper.writableDatabase
        refreshTodoList()
        findViewById<LinearLayout>(R.id.create_new_list).setOnClickListener {
            createTodo()
        }
    }


    @SuppressLint("Recycle")
    private fun createTodo() {
        val cursor = (db?: dbHelper.writableDatabase).query("todolist", null, null, null, null, null, null, null)
        if (cursor.count == 10) {
            Toast.makeText(this, "任务数量过多", Toast.LENGTH_SHORT).show()
        } else {
            val view = LayoutInflater.from(this).inflate(R.layout.layout_dialog_new_todo, null)
            AlertDialog.Builder(this).apply {
                setTitle("新建列表")
                setView(view)
                setNegativeButton("取消") { dialog, which ->
                }
                setPositiveButton("创建列表") { p0, p1 ->
                    val title = view.findViewById<EditText>(R.id.et_dialog)?.text.toString()
                    if (title.isEmpty()) {
                        Toast.makeText(this@TodoListActivity, "列表不能为空", Toast.LENGTH_SHORT).show()
                        createTodo()
                    } else {
                        Toast.makeText(this@TodoListActivity, "添加成功", Toast.LENGTH_SHORT).show()
                        val values = ContentValues().apply {
                            put("title", title)
                        }
                        db?.insert("todolist", null, values)  //添加values到db数据库的todolist表
                        refreshTodoList()
                    }
                }
                show()
            }
        }
    }
    @SuppressLint("NotifyDataSetChanged", "Range")
    private fun refreshTodoList() {

        val cursor = (db?: dbHelper.writableDatabase).query("todolist", null, null, null, null, null, null, null)
        val data = mutableListOf<String>()
        if (cursor.moveToFirst()) {
            do {
                data.add(cursor.getString(cursor.getColumnIndex("title")))
            } while (cursor.moveToNext())
        }
        cursor.close()
        val adapter = TodoListAdapter(data)
        todoListView?.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}