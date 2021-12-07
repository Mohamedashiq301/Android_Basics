package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        CheckLoginStatus();
    }

    private void CheckLoginStatus() {
        //if logged in ->show MyNotesActivity
        //else ->show LoginActivity
    }
}