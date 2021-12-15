package com.example.myapp.views

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.utils.PrefConstant
import com.example.myapp.R

class SplashActivity : AppCompatActivity() {

    lateinit var sharedPreferences:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupSharedPreference()
        checkLoginStatus()
    }



    private fun setupSharedPreference() {
        sharedPreferences=getSharedPreferences(PrefConstant.Shared_Preference_Notes, MODE_PRIVATE)
    }

    private fun checkLoginStatus() {
        val isLoggedin =sharedPreferences.getBoolean(PrefConstant.Is_Logged_In,false)
        if(isLoggedin){
            val intent = Intent(this@SplashActivity, MyNotesActivity::class.java)
            startActivity(intent)
        }else{
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
