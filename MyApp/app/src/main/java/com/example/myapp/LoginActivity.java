package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText EditTextFullName,EditTextUserName;
    Button ButtonLogin;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditTextFullName=findViewById(R.id.EditTextFullName);
        EditTextUserName=findViewById(R.id.EditTextUserName);
        ButtonLogin=findViewById(R.id.ButtonLogin);
        setSharedPreferences();

        View.OnClickListener clickAction= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname=EditTextFullName.getText().toString();
                Intent intent=new Intent(LoginActivity.this,MyNotesActivity.class);
                intent.putExtra(AppConstant.Full_Name,fullname);
              startActivity(intent);
            }
        };
        ButtonLogin.setOnClickListener(clickAction);
    }

    private void setSharedPreferences() {
        sharedPreferences=getSharedPreferences(PrefConstant.Shared_Preference_Notes,MODE_PRIVATE);
    }
}