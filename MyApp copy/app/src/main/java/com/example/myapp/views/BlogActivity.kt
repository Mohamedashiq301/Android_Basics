package com.example.myapp.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.Adapter.BlogAdapter
import com.example.myapp.R
import com.androidnetworking.error.ANError

import org.json.JSONArray

import com.androidnetworking.interfaces.JSONArrayRequestListener

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority


class BlogActivity : AppCompatActivity() {

    lateinit var recyclerviewBlogs:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog)
        bindViews()
        getBlogs()
    }

    private fun getBlogs() {
        AndroidNetworking.get("https://fierce-cove-29863.herokuapp.com/getAllUsers/{pageNumber}")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(object : JSONArrayRequestListener {
                    override fun onResponse(response: JSONArray) {
                        // do anything with response
                    }

                    override fun onError(error: ANError) {
                        // handle error
                    }
                })
    }

    private fun bindViews() {
        recyclerviewBlogs=findViewById(R.id.recyclerviewBlogs)
    }

    private fun setupRecyclerView(){
        val BlogAdapter=BlogAdapter()
        val linearLayoutManager=LinearLayoutManager(this)
        linearLayoutManager.orientation=RecyclerView.VERTICAL
        recyclerviewBlogs.layoutManager=linearLayoutManager
        recyclerviewBlogs.adapter=BlogAdapter
    }
}