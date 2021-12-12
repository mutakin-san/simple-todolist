package com.mutakin.simpletodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var etTitle: EditText
    private lateinit var btnInsert: Button
    private lateinit var btnDelete: Button
    private lateinit var rvTodos: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = TodoAdapter(mutableListOf())
        etTitle = findViewById(R.id.etTitle)
        btnInsert = findViewById(R.id.btn_insert)
        btnDelete = findViewById(R.id.btn_delete)
        rvTodos = findViewById(R.id.rvTodos)

        rvTodos.adapter = todoAdapter
        rvTodos.layoutManager = LinearLayoutManager(this)



        btnInsert.setOnClickListener {
            val title = etTitle.text.trim().toString()
            val todo = Todo(title = title, isChecked = false)
            todoAdapter.addTodo(todo)
            etTitle.text.clear()
        }


        btnDelete.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }


    }
}