package com.example.myapp.views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.myapp.Adapter.BlogAdapter
import com.example.myapp.R
import com.example.myapp.model.JsonResponse


class BlogActivity : AppCompatActivity() {

    lateinit var recyclerviewBlogs:RecyclerView
    val TAG="BlogActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog)
        bindViews()
        getBlogs()
    }

    private fun getBlogs() {
        AndroidNetworking.get("https://www.mocky.io/v2/5926ce9d11000096006ccb30")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(JsonResponse::class.java,object:ParsedRequestListener<JsonResponse>{
                    override fun onResponse(response: JsonResponse?) {
                        setupRecyclerView(response)
                    }

                    override fun onError(anError: ANError?) {
                        Log.d(TAG, anError!!.localizedMessage)
                    }

                })
                }


    private fun bindViews() {
        recyclerviewBlogs=findViewById(R.id.recyclerviewBlogs)
    }

    private fun setupRecyclerView(response: JsonResponse?) {
        val blogAdapter=BlogAdapter(response!!.data)
        val linearLayoutManager=LinearLayoutManager(this)
        linearLayoutManager.orientation=RecyclerView.VERTICAL
        recyclerviewBlogs.layoutManager=linearLayoutManager
        recyclerviewBlogs.adapter=blogAdapter
    }
}