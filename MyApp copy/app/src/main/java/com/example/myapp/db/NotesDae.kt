package com.example.myapp.db

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

//data access objects -DAO
@Dao
interface  NotesDae{
    @Query("SELECT *from notesData")
    fun getall():List<Notes>

    @Insert(onConflict = REPLACE)
    fun insert(notes: Notes)

    @Update
    fun update(notes: Notes)

    @Delete
    fun delete(notes: Notes)
}