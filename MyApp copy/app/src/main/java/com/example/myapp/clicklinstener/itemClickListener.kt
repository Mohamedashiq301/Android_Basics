package com.example.myapp.clicklinstener

import com.example.myapp.data.local.db.Notes


interface itemClickListener {
    fun onClick(notes: Notes)
    fun onUpdate(notes: Notes)
}