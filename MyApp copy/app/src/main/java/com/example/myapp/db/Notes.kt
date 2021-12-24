package com.example.myapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.lang.reflect.Constructor


@Entity(tableName="notesData")
data class Notes @Ignore constructor(


    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "description")
    var description: String = "",

    @ColumnInfo(name = "imagePath")
    var imagePath: String = "",

    @ColumnInfo(name = "isTaskCompleted")
    var isTaskCompleted: Boolean = false
    )
