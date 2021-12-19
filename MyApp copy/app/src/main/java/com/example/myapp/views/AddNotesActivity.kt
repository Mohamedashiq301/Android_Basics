package com.example.myapp.views

import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.myapp.R

class AddNotesActivity : AppCompatActivity() {
    lateinit var EditTextTitle: EditText
    lateinit var EditTextDescription: TextView
    lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)
        bindViews()
        clickListener()
    }

    private fun clickListener() {
        submitButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

            }

        })
    }

    private fun bindViews() {
        EditTextTitle = findViewById(R.id.EditTextTitle)
        EditTextDescription = findViewById(R.id.EditTextDescription)
        submitButton = findViewById(R.id.SubmitButton)
    }
}