package com.example.myapp.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R

class BlogActivity : AppCompatActivity() {

    lateinit var recyclerviewBlogs:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog)
        bindViews()
    }

    private fun bindViews() {
        recyclerviewBlogs=findViewById(R.id.recyclerviewBlogs)
    }

    private fun setupRecyclerView(){

    }
}