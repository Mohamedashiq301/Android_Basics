package com.example.myapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class MyNotesActivity extends AppCompatActivity {

    String fullname;
    FloatingActionButton fabAddNotes;
    TextView textViewTitle,textViewDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        bindView();
        getintentData();

        fabAddNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupDialogBox();
            }
        });
        getSupportActionBar().setTitle(fullname);
    }

    private void getintentData() {
        Intent intent=getIntent();
        fullname=intent.getStringExtra(AppConstant.Full_Name);
    }

    private void bindView() {
        fabAddNotes=findViewById(R.id.fabAddNotes);
        textViewTitle=findViewById(R.id.textViewTitle);
        textViewDescription=findViewById(R.id.textViewDescription);
    }

    private void setupDialogBox() {
        View view= LayoutInflater.from(MyNotesActivity.this).inflate(R.layout.add_notes_dialog_layout,null);
        EditText EditTextTitle=view.findViewById(R.id.EditTextTitle);
        EditText EditTextDescription=view.findViewById(R.id.EditTextDescription);
        Button SubmitButton=view.findViewById(R.id.ButtonSubmit);

        //Dialog
        final AlertDialog dialog=new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();
        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewTitle.setText(EditTextTitle.getText().toString());
                textViewDescription.setText(EditTextDescription.getText().toString());
                dialog.hide();
            }
        });
        dialog.show();

    }
}