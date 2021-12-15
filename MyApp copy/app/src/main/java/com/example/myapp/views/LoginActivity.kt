package com.example.myapp.views

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.utils.PrefConstant
import com.example.myapp.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(){

    lateinit var EditTextFullName: TextView
    lateinit var EditTextUserName: TextView
    lateinit var buttonLogin:Button
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bindView()
        setupSharedPreference()
    }

    private fun setupSharedPreference() {
        sharedPreferences=getSharedPreferences(PrefConstant.Shared_Preference_Notes, MODE_PRIVATE)
    }

    private fun bindView() {
        EditTextFullName=findViewById(R.id.EditTextFullName)
        EditTextUserName=findViewById(R.id.EditTextUserName)
        buttonLogin=findViewById(R.id.ButtonLogin)

        val clickAction= object : View.OnClickListener {
            override fun onClick(v: View?) {
                val fullName=EditTextFullName.text.toString()
                val userName=EditTextUserName.text.toString()
                if(fullName.isNotEmpty() && userName.isNotEmpty()){
                    val intent=Intent(this@LoginActivity, MyNotesActivity::class.java)
                    startActivity(intent)
                    saveFullName(fullName)
                    saveLoginStatus()
                }else{

                }
            }

        }
        buttonLogin.setOnClickListener(clickAction)
    }

    private fun saveLoginStatus() {
        editor=sharedPreferences.edit()
        editor.putBoolean(PrefConstant.Is_Logged_In,true)
        editor.apply()
    }

    private fun saveFullName(fullName: String) {
        editor=sharedPreferences.edit()
        editor.putString("fullName",fullName)
        editor.apply()
    }
}