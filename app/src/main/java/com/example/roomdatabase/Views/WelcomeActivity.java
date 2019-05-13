package com.example.roomdatabase.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.roomdatabase.R;

public class WelcomeActivity extends AppCompatActivity {

    Button insertStudent, viewStudents, editCollege;

    TextView welcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        insertStudent = (Button) findViewById(R.id.insert_student);
        viewStudents = (Button) findViewById(R.id.view_student);
        editCollege = (Button) findViewById(R.id.edit_college);
        welcomeTextView = (TextView) findViewById(R.id.welcome_tv);
        String user = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            user = getIntent().getStringExtra("USERNAME_EXTRA");

        welcomeTextView.setText("Welcome " + user);
        viewStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WelcomeActivity.this, ViewStudent.class);
                startActivity(i);
            }
        });

        insertStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WelcomeActivity.this, InsertStudent.class);
                startActivity(i);
            }
        });
        editCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WelcomeActivity.this, EditCollege.class);
                startActivity(i);
            }
        });


    }
}
