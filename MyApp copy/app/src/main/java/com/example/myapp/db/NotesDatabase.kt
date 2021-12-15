package com.example.myapp.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

abstract class NotesDatabase:RoomDatabase(){
    abstract fun notesDao():NotesDae

    companion object{
        lateinit var INSTANCE: NotesDatabase
        fun getInstance(context: Context):NotesDatabase {
            synchronized(NotesDatabase::class){
                INSTANCE=Room.databaseBuilder(context.applicationContext,NotesDatabase::class.java,"my-notes.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }
}