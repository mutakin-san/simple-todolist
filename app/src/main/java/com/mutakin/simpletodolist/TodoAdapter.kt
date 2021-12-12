package com.mutakin.simpletodolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(private val todos: MutableList<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val cbIsChecked: CheckBox = itemView.findViewById(R.id.cbIsChecked)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var todo = todos[position]


        holder.apply {
            tvTitle.text = todo.title
            cbIsChecked.isChecked = todo.isChecked
            toggleStrikeThroughText(tvTitle, todo.isChecked)
            cbIsChecked.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThroughText(tvTitle, isChecked)
                todo.isChecked = !todo.isChecked
            }

        }
    }

    private fun toggleStrikeThroughText(tvTitle: TextView, checked: Boolean) {
        if(checked){
            tvTitle.paintFlags = tvTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            tvTitle.paintFlags = tvTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    fun addTodo(todo: Todo){
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }


    fun deleteDoneTodos(){
        todos.removeAll { todo -> todo.isChecked }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return todos.size
    }


}