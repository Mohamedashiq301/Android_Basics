package com.example.myapp.splash

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.R
import com.example.myapp.data.local.pref.PrefConstant
import com.example.myapp.onboarding.OnBroadingActivity
import com.example.myapp.views.LoginActivity
import com.example.myapp.views.MyNotesActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId

class SplashActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var handler: Handler
    lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupSharedPreference()
        goToNext()
        //checkLoginStatus()
        //getFCMToken()
    }

    private fun goToNext() {
        handler= Handler()
        runnable= Runnable {
            checkLoginStatus()
        }
        handler.postDelayed(runnable,2000)
    }

    private fun getFCMToken() {
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("SplashActivity", "getInstanceFailed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result?.token

            // Log and toast
            Log.d("SplashActivity", token.toString())
            Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
        })
    }


    private fun setupSharedPreference() {
        sharedPreferences = getSharedPreferences(PrefConstant.Shared_Preference_Notes, MODE_PRIVATE)
    }

    private fun checkLoginStatus() {
        val isLoggedin = sharedPreferences.getBoolean(PrefConstant.Is_Logged_In, false)
        val onBoardedSuccess=sharedPreferences.getBoolean(PrefConstant.ON_BOARDED_SUCCESSFULLY,false)
        if (isLoggedin) {
            val intent = Intent(this@SplashActivity, MyNotesActivity::class.java)
            startActivity(intent)
        } else {
            //if onboarding success->Login
                // else ->onboarding activity
            if (onBoardedSuccess) {
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
            }else{
                val intent=Intent(this@SplashActivity,OnBroadingActivity::class.java)
                startActivity(intent)
            }
        }
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        handler.removeCallbacks(runnable)
    }
}
