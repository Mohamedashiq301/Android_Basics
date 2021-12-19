package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setupSharedPreference();
        CheckLoginStatus();
    }

    private void setupSharedPreference() {
        sharedPreferences=getSharedPreferences(PrefConstant.Shared_Preference_Notes,MODE_PRIVATE);
    }

    private void CheckLoginStatus() {
        //if logged in ->show MyNotesActivity
        //else ->show LoginActivity
        boolean isloggedin=sharedPreferences.getBoolean(PrefConstant.Is_Logged_In,false);
            if (isloggedin){
                Intent intent=new Intent(SplashActivity.this,MyNotesActivity.class);
                startActivity(intent);
            }else {
                Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        }
    }
