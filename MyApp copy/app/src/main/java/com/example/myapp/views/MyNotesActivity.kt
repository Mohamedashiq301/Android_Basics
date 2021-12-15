package com.example.myapp.views

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.Adapter.NotesAdapter
import com.example.myapp.utils.AppConstant
import com.example.myapp.utils.PrefConstant
import com.example.myapp.R
import com.example.myapp.clicklinstener.itemClickListener
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
        val EditTextTitle=view.findViewById<EditText>(R.id.EditTextTitle)
        val EditTextDescription=view.findViewById<EditText>(R.id.EditTextDescription)
        val SubmitButton=view.findViewById<Button>(R.id.SubmitButton)
        val dialog=AlertDialog.Builder(this)
            .setView(view)
            .setCancelable(false)
            .create()
        SubmitButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val title=EditTextTitle.text.toString()
                val description=EditTextDescription.text.toString()
                if (title.isNotEmpty() && description.isNotEmpty()){
                    val notes=Notes(title, description)
                    notesList.add(notes)
                }else{
                    Toast.makeText(this@MyNotesActivity,"Title and Description can't be Empty",Toast.LENGTH_SHORT).show()
                }
                setRecyclerView()

            }

        })
    }

    private fun setRecyclerView() {
        val itemClickListener=object:itemClickListener{
            override fun onClick(notes: Notes) {
                val intent= Intent(this@MyNotesActivity, DetailActivity::class.java)
                intent.putExtra(AppConstant.TITLE,notes.title)
                intent.putExtra(AppConstant.DESCRIPTION,notes.description)
                startActivity(intent)
            }

        }
        val notesAdapter=NotesAdapter(notesList, itemClickListener)
        val linearLayoutManager=LinearLayoutManager(this@MyNotesActivity)
        linearLayoutManager.orientation=RecyclerView.VERTICAL
        recycleView.layoutManager=linearLayoutManager
        recycleView.adapter=notesAdapter
    }

    private fun getIntentData() {
        val intent=intent
        if(intent.hasExtra(AppConstant.Full_Name)) {
            fullname = intent.getStringExtra(AppConstant.Full_Name).toString()
        }
        if (fullname.isEmpty()){
            fullname= sharedPreferences.getString(PrefConstant.full_name,"").toString()
        }
    }

    private fun setupSharedPreference() {
        sharedPreferences=getSharedPreferences(PrefConstant.Shared_Preference_Notes, MODE_PRIVATE)
    }

    private fun bindView() {
        fabAddNotes=findViewById(R.id.fabAddNotes)
        recycleView=findViewById(R.id.recyclerviewNotes)
    }
}