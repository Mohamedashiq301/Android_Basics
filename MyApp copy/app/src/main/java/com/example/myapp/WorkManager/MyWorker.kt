package com.example.myapp.WorkManager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.myapp.NotesApp

class MyWorker(val context: Context,val workerParameters: WorkerParameters):Worker(context,workerParameters) {
    override fun doWork(): Result {
        val notesApp=applicationContext as NotesApp
        val notesDae=notesApp.getNotesDb().notesDao()
        notesDae.deleteNotes(true)
        return Result.success()
    }
}