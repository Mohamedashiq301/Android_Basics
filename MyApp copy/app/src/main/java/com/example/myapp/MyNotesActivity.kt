package com.example.myapp

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.model.Notes
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MyNotesActivity : AppCompatActivity(){
    lateinit var fullname: String
    lateinit var fabAddNotes:FloatingActionButton
    lateinit var sharedPreferences: SharedPreferences
    lateinit var recycleView: RecyclerView
    var notesList=ArrayList<Notes>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_notes)
        bindView()
        setupSharedPreference()
        getIntentData()
        supportActionBar?.title =fullname
        fabAddNotes.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                setDialogBox()
            }

        })
    }

    private fun setDialogBox() {
        val view=LayoutInflater.from(this@MyNotesActivity).inflate(R.layout.add_notes_dialog_layout,null)

    }

    private fun getIntentData() {
        val intent=intent
        fullname = intent.getStringExtra(AppConstant.Full_Name).toString()
        if (fullname.isEmpty()){
            fullname= sharedPreferences.getString(PrefConstant.full_name,"").toString()
        }
    }

    private fun setupSharedPreference() {
        sharedPreferences=getSharedPreferences(PrefConstant.Shared_Preference_Notes,"")
    }

    private fun bindView() {
        fabAddNotes=findViewById(R.id.fabAddNotes)
        recycleView=findViewById(R.id.recyclerviewNotes)
    }
}