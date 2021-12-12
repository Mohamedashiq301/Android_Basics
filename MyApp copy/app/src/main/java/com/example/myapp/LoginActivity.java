package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText EditTextFullName,EditTextUserName;
    Button ButtonLogin;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

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
                String username=EditTextUserName.getText().toString();
                if(!TextUtils.isEmpty(fullname) && !TextUtils.isEmpty(username)) {
                    Intent intent = new Intent(LoginActivity.this, MyNotesActivity.class);
                    intent.putExtra(AppConstant.Full_Name, fullname);
                    startActivity(intent);
                    //Login
                    saveLoginstatus();
                    savefullname(fullname);
                }else {
                    Toast.makeText(LoginActivity.this, "FullName and UserName Can't be empty", Toast.LENGTH_SHORT).show();
                }
            }
        };
        ButtonLogin.setOnClickListener(clickAction);
    }

    private void savefullname(String fullname) {
        editor=sharedPreferences.edit();
        editor.putString(PrefConstant.full_name,fullname);
        editor.apply();

    }

    private void saveLoginstatus() {
        editor=sharedPreferences.edit();
        editor.putBoolean(PrefConstant.Is_Logged_In,true);
        editor.apply();
    }

    private void setSharedPreferences() {
        sharedPreferences=getSharedPreferences(PrefConstant.Shared_Preference_Notes,MODE_PRIVATE);
    }
}