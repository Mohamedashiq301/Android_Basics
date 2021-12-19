package com.example.myapp.views

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.Adapter.NotesAdapter
import com.example.myapp.NotesApp
import com.example.myapp.R
import com.example.myapp.clicklinstener.itemClickListener
import com.example.myapp.db.Notes
import com.example.myapp.utils.AppConstant
import com.example.myapp.utils.PrefConstant
import com.google.android.material.floatingactionbutton.FloatingActionButton

public class MyNotesActivity :AppCompatActivity(){

    lateinit var fullName:String
    lateinit var fabAddNotes:FloatingActionButton
    lateinit var sharedPreferences: SharedPreferences
    lateinit var recyclerView: RecyclerView
    var notesList=ArrayList<Notes>()
    var TAG="MyNotesActivity"


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_notes)
        bindViews()
        SetupSharedPreference()
        getIntentData()
        getDataFromDataBase()
        setupToolbarText()
        setupRecyclerView()

        fabAddNotes.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                setupDialogBox()
            }

        })
    }

    private fun getDataFromDataBase() {
        val notesApp=applicationContext as NotesApp
        val notesDae=notesApp.getNotesDb().notesDao()
        notesList.addAll(notesDae.getAll())
    }

    private fun setupToolbarText() {
        if (supportActionBar!=null) {
            supportActionBar?.title = fullName
        }
    }

    private fun setupDialogBox() {
        val view=LayoutInflater.from(this@MyNotesActivity).inflate(R.layout.add_notes_dialog_layout,null)
        val EditTextTitle=findViewById<EditText>(R.id.EditTextTitle)
        val EditTextDescription=findViewById<EditText>(R.id.EditTextDescription)
        val submitButton=findViewById<EditText>(R.id.submitButton)
        val dialog=AlertDialog.Builder(this)
            .setView(view)
            .setCancelable(false)
            .create()
        submitButton.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                val title=EditTextTitle.text.toString()
                val description=EditTextDescription.text.toString()
                if (title.isNotEmpty()&&description.isNotEmpty()){
                    val notes=Notes(title = title, description = description)
                    notesList.add(notes)
                }else{
                    Toast.makeText(this@MyNotesActivity,"Title and description can't be empty",Toast.LENGTH_SHORT).show()
                }


                getNotesToDb(notes)
                dialog.hide()
            }

        })
        dialog.show()

    }

    private fun getNotesToDb(notes: Notes) {
        val notesApp=applicationContext as NotesApp
        val notesDae=notesApp.getNotesDb().notesDao()
        notesDae.insert(notes)
    }




    private fun setupRecyclerView() {
       val ItemClickListener=object :itemClickListener{
           override fun onClick(notes: Notes) {
                val intent=Intent(this@MyNotesActivity,DetailActivity::class.java)
               intent.putExtra(AppConstant.TITLE,notes.title)
               intent.putExtra(AppConstant.DESCRIPTION,notes.description)
               startActivity(intent)
           }

           override fun onUpdate(notes: Notes) {
                Log.d(TAG,notes.isTaskCompleted.toString())

               val notesApp=applicationContext as NotesApp
               val notesDae=notesApp.getNotesDb().notesDao()
               notesDae.update(notes)
           }
       }
        val notesAdapter=NotesAdapter(notesList,ItemClickListener)
        val linearLayoutManager=LinearLayoutManager(this@MyNotesActivity)
        recyclerView.layoutManager=linearLayoutManager
        recyclerView.adapter=notesAdapter

    }

    private fun getIntentData() {
        val intent=intent
        fullName= intent.getStringExtra(AppConstant.Full_Name).toString()
        if (fullName.isEmpty()){
            fullName= sharedPreferences.getString(PrefConstant.Shared_Preference_Notes,"").toString()
        }

    }

    private fun SetupSharedPreference() {
        sharedPreferences=getSharedPreferences(PrefConstant.Shared_Preference_Notes, MODE_PRIVATE)
    }

    private fun bindViews() {
        fabAddNotes=findViewById(R.id.fabAddNotes)
        recyclerView=findViewById(R.id.recyclerviewNotes)
    }
}