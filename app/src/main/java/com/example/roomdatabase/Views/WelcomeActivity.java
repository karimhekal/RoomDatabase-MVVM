package com.example.roomdatabase.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.roomdatabase.R;

public class WelcomeActivity extends AppCompatActivity {

    Button insertStudent, viewStudents, editCollege;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        insertStudent=(Button) findViewById(R.id.insert_student);
        viewStudents=(Button) findViewById(R.id.view_student);
        editCollege=(Button) findViewById(R.id.edit_college);

        viewStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WelcomeActivity.this,ViewStudent.class);
                startActivity(i);
            }
        });

        insertStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WelcomeActivity.this,InsertStudent.class);
                startActivity(i);
            }
        });
        editCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WelcomeActivity.this,EditCollege.class);
                startActivity(i);
            }
        });




    }
}
