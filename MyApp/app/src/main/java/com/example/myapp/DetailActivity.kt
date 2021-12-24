package com.example.myapp

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity:AppCompatActivity(){

    val TAG="DetailActivity"
    lateinit var textViewTitle:TextView
    lateinit var textViewDescription:TextView

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_detail)
        bindViews()
        setIntentData()
    }

    private fun setIntentData() {
        val intent=intent
        val title=intent.getStringExtra(AppConstant.TITLE)
        val description=intent.getStringExtra(AppConstant.DESCRIPTION)
        //SetText
        textViewTitle.text=title
        textViewDescription.text=description
    }

    private fun bindViews() {
        textViewTitle=findViewById(R.id.textViewTitle)
        textViewDescription=findViewById(R.id.textViewDescription)
    }
}