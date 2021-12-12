package com.example.myapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import android.widget.Toast;

import com.example.myapp.Adapter.NotesAdapter;
import com.example.myapp.clicklinstener.ItemClickListener;
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
            if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(description)){
            Notes notes=new Notes();
            notes.setTitle(title);
            notes.setDescription(description);
            notesList.add(notes);
            }else{
                Toast.makeText(MyNotesActivity.this, "Title and Description can't be empty", Toast.LENGTH_SHORT).show();
            }
            

            setupRecyclerView();

                dialog.hide();
            }
        });
        dialog.show();

    }

    private void setupRecyclerView() {

        //Interface
        ItemClickListener itemClickListener=new ItemClickListener() {
            @Override
            public void onClick(Notes notes) {
                Intent intent=new Intent(MyNotesActivity.this,DetailActivity.class);
                intent.putExtra(AppConstant.TITLE,notes.getTitle());
                intent.putExtra(AppConstant.DESCRIPTION,notes.getDescription());
                startActivity(intent);
            }
        };


        NotesAdapter notesAdapter=new NotesAdapter(notesList,itemClickListener);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MyNotesActivity.this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        recyclerviewNotes.setLayoutManager(linearLayoutManager);
        recyclerviewNotes.setAdapter(notesAdapter);
    }
}