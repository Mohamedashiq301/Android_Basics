package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView textviewTitle,textviewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        bindView();
        setupIntentData();
    }

    private void setupIntentData() {
        Intent intent = getIntent();
        String title=intent.getStringExtra(AppConstant.TITLE);
        String description=intent.getStringExtra(AppConstant.DESCRIPTION);
        textviewTitle.setText(title);
        textviewDescription.setText(description);
    }

    private void bindView() {
        textviewTitle=findViewById(R.id.textViewTitle);
        textviewDescription=findViewById(R.id.textViewDescription);
    }
}