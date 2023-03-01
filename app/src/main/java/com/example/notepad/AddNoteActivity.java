package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;

public class AddNoteActivity extends AppCompatActivity {

    EditText title;
    EditText description;
    Button savebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
title=findViewById(R.id.titleedittext);
description=findViewById(R.id.descriptionedittext);
        savebtn = findViewById(R.id.savebutton);


        Realm.init(getApplicationContext());
        Realm realm=Realm.getDefaultInstance();

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tittle = title.getText().toString();
                String descriptn = description.getText().toString();
                long createdtime = System.currentTimeMillis();

                realm.beginTransaction();
                Note note = realm.createObject(Note.class);
                note.setTitle(tittle);
                note.setDescription(descriptn);
                note.getCreatedTime(createdtime);
                realm.commitTransaction();
                Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG).show();
                finish();

            }
        });





    }
}