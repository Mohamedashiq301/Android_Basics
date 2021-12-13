package com.example.myapp

import android.accounts.AuthenticatorDescription
import android.nfc.Tag
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout

class DetailActivity : AppCompatActivity(){

    lateinit var textviewTitle:TextView
    lateinit var textviewDescription:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        bindView()
        setupIntentData()
    }

    private fun setupIntentData() {
        //getIntent()
        val intent= intent
        val title=intent.getStringExtra(AppConstant.TITLE)
        val description=intent.getStringExtra(AppConstant.DESCRIPTION)
        //setText()
        textviewTitle.text = title
        textviewDescription.text = description
    }
    private fun bindView() {
        textviewTitle=findViewById(R.id.textViewTitle)
        textviewDescription=findViewById(R.id.textViewDescription)
    }


}