package com.example.myapp

import android.app.Application
import com.example.myapp.db.NotesDatabase

class NotesApp :Application(){
    override fun onCreate() {
        super.onCreate()
    }
    fun getNotesDb():NotesDatabase{
        return NotesDatabase.getInstance(this)
    }
}