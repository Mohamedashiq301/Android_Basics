package com.example.myapp

import com.example.myapp.PrefConstant

object PrefConstant {
    @JvmField
    var Shared_Preference_Notes = "NotesAppPerf"
    @JvmField
    var Is_Logged_In = "is_logged_in"
    @JvmField
    var full_name: String? = null

    init {
        full_name = "fullname"
    }
}