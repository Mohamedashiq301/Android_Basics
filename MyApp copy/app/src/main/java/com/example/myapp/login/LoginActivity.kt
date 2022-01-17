package com.example.myapp.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.R
import com.example.myapp.data.local.pref.PrefConstant
import com.example.myapp.views.MyNotesActivity

//import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var EditTextFullName: EditText
    lateinit var EditTextUserName: EditText
    lateinit var buttonLogin: Button
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bindView()
        setupSharedPreference()
    }

    private fun setupSharedPreference() {
        sharedPreferences = getSharedPreferences(PrefConstant.Shared_Preference_Notes, MODE_PRIVATE)
    }

    private fun bindView() {
        EditTextFullName = findViewById(R.id.editTextFullName)
        EditTextUserName = findViewById(R.id.editTextUserName)
        buttonLogin = findViewById(R.id.buttonlogin)

        val clickAction = object : View.OnClickListener {
            override fun onClick(v: View?) {
                val fullName = EditTextFullName.text.toString()
                val userName = EditTextUserName.text.toString()
                if (fullName.isNotEmpty() && userName.isNotEmpty()) {
                    val intent = Intent(this@LoginActivity, MyNotesActivity::class.java)
                    intent.putExtra(fullName,true)
                    startActivity(intent)
                    saveFullName(fullName)
                    saveLoginStatus()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "FullName and UserName can't be empty",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
        buttonLogin.setOnClickListener(clickAction)
    }

    private fun saveLoginStatus() {
        editor = sharedPreferences.edit()
        editor.putBoolean(PrefConstant.Is_Logged_In, true)
        editor.apply()
    }

    private fun saveFullName(fullName: String) {
        editor = sharedPreferences.edit()
        editor.putString(PrefConstant.full_name, fullName)
        editor.apply()
    }
}