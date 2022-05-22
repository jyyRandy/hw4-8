package com.bytedance.homework.homework6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.homework.R

class TodoListAdapter(private val todoList: List<String>): RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_to_do_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = todoList[position]
        holder.category.text = todo
        if (position == 3) {
            holder.devider.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val category: TextView = itemView.findViewById(R.id.category)
        val devider: View = itemView.findViewById(R.id.v_devider)
    }

}