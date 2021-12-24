package com.example.myapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity:AppCompatActivity() {

    lateinit var EditTextFullName:TextView
    lateinit var EditTextUserName:TextView
    lateinit var ButtonLogin:Button
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_login)
        bindViews()
        setupSharedPreference()
    }

    private fun setupSharedPreference() {
        sharedPreferences=getSharedPreferences(PrefConstant.Shared_Preference_Notes, MODE_PRIVATE)
    }

    private fun bindViews() {
        EditTextFullName=findViewById(R.id.EditTextFullName)
        EditTextUserName=findViewById(R.id.EditTextUserName)
        ButtonLogin=findViewById(R.id.ButtonLogin)

        val clickAction=object:View.OnClickListener{
            override fun onClick(v: View?) {
                val fullName=EditTextFullName.text.toString()
                val userName=EditTextUserName.text.toString()
                if (fullName.isNotEmpty()&&userName.isNotEmpty()){
                    val intent=Intent(this@LoginActivity,MyNotesActivity::class.java)
                    startActivity(intent)
                    saveFullName(fullName)
                    saveLoginStatus()
                }else{
                    Toast.makeText(this@LoginActivity,"Fullname and Username can't be empty",Toast.LENGTH_SHORT).show()
                }
            }

        }

        ButtonLogin.setOnClickListener(clickAction)
    }

    private fun saveLoginStatus() {
        editor=sharedPreferences.edit()
        editor.putBoolean(PrefConstant.Is_Logged_In,true)
        editor.apply()
    }

    private fun saveFullName(fullName: String) {
        editor=sharedPreferences.edit()
        editor.putString(PrefConstant.full_name,fullName)
        editor.apply()
    }
}