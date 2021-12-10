package com.example.myapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapp.model.Notes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MyNotesActivity extends AppCompatActivity {

    String fullname;
    FloatingActionButton fabAddNotes;
    SharedPreferences sharedPreferences;
    RecyclerView recyclerviewNotes;
    ArrayList<Notes> notesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        bindView();
        setSharedPreference();

        Intent intent=getIntent();
        fullname=intent.getStringExtra(AppConstant.Full_Name);
        if (TextUtils.isEmpty(fullname)) {
            fullname = sharedPreferences.getString(PrefConstant.full_name,"");

        fabAddNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupDialogBox();
            }
        });
        getSupportActionBar().setTitle(fullname);
    }

    }

    private void setSharedPreference() {
        sharedPreferences=getSharedPreferences(PrefConstant.Shared_Preference_Notes,MODE_PRIVATE);
    }

    private void bindView() {
        fabAddNotes=findViewById(R.id.fabAddNotes);
        recyclerviewNotes=findViewById(R.id.recyclerviewNotes);
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
            String title=EditTextTitle.getText().toString();
            String description=EditTextDescription.getText().toString();
            Notes notes=new Notes();
            notes.setTitle(title);
            notes.setDescription(description);
            notesList.add(notes);
                dialog.hide();
            }
        });
        dialog.show();

    }
}