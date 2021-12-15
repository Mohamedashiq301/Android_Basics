package com.example.myapp.clicklinstener

import com.example.myapp.model.Notes

interface itemClickListener {
    fun onClick(notes:Notes)
    fun onUpdate(notes: Notes)
}