package com.example.myapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity(){

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_splash)
        setupSharedPreference()
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        val isLoggedIn=sharedPreferences.getBoolean(PrefConstant.Is_Logged_In,false)
        if (isLoggedIn){
            val intent=Intent(this@SplashActivity,MyNotesActivity::class.java)
            startActivity(intent)
        }else{
            val intent=Intent(this@SplashActivity,LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupSharedPreference() {
        sharedPreferences=getSharedPreferences(PrefConstant.Is_Logged_In, MODE_PRIVATE)

    }
}