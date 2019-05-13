package com.example.roomdatabase.Views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;


import android.os.Bundle;

import com.example.roomdatabase.Adapter.CollegeAdapter;
import com.example.roomdatabase.Adapter.StudentAdapter;
import com.example.roomdatabase.Model.College;
import com.example.roomdatabase.Model.Student;
import com.example.roomdatabase.ViewModels.CollegeViewModel;
import com.example.roomdatabase.ViewModels.StudentViewModel;
import com.example.roomdatabase.R;

import java.util.ArrayList;
import java.util.List;

public class InsertStudent extends AppCompatActivity {

    public static final String EXTRA_NAME = "com.example.roomdatabase.EXTRA_NAME";
    public static final String EXTRA_COLLEGE = "com.example.roomdatabase.EXTRA_COLLEGE";
    public static final String EXTRA_GENDER = "com.example.roomdatabase.EXTRA_GENDER";
    public static final String EXTRA_PASSWORD = "com.example.roomdatabase.EXTRA_PASSWORD";
    public static final String EXTRA_ID = "com.example.roomdatabase.EXTRA_ID";
    public static final String EXTRA_CGPA = "com.example.roomdatabase.EXTRA_CGPA";
    public static final String EXTRA_PRIORITY = "com.example.roomdatabase.EXTRA_PRIORITY";
    StudentViewModel studentViewModel;
    // private NumberPicker numberPickerPriority;
    Spinner collegeSpinner;
    ArrayAdapter<String> adapter;
    CollegeViewModel collegeViewModel;
    private EditText editTextName;
    private EditText editTextCGPA;
    private EditText editTextCollege, editTextGender, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note); // init View
        editTextName = findViewById(R.id.edit_text_name);
        editTextCGPA = findViewById(R.id.edit_text_cgpa);
        editTextCollege = findViewById(R.id.edit_text_college);
        editTextGender = findViewById(R.id.edit_text_gender);
        editTextPassword = findViewById(R.id.edit_text_password);
        collegeSpinner = (Spinner) findViewById(R.id.spinner_college);
        final ArrayList<String> collegesList = new ArrayList<>();
        adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.small_view, collegesList);

        final CollegeAdapter collegeAdapter = new CollegeAdapter();
        collegeViewModel = ViewModelProviders.of(this).get(CollegeViewModel.class);
        collegeViewModel.getAllColleges().observe(this, new Observer<List<College>>() {
            @Override
            public void onChanged(List<College> colleges) {
                //this is what happens when when data changes // the code bellow should be related to changing the view so it displays the d
                //Toast.makeText(context, "onChanged", Toast.LENGTH_SHORT).show(); // this toast was to make sure that the live data is changed
                collegeAdapter.setColleges(colleges);  // getting colleges from database and assigning it to collegeadapter
                addElements(collegeAdapter, collegesList);
            }
        });

        //  collegeSpinner.setAdapter(adapter);

        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Student");


        collegeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) view).setTextColor(Color.BLACK);
                Toast.makeText(InsertStudent.this, ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        getIntents();
    }

    private void getIntents() {
        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Student");
            editTextName.setText(intent.getStringExtra(EXTRA_NAME));
            editTextCGPA.setText(intent.getStringExtra(EXTRA_CGPA));
            editTextCollege.setText(intent.getStringExtra(EXTRA_COLLEGE)); // spinner
            editTextGender.setText(intent.getStringExtra(EXTRA_GENDER));
            editTextPassword.setText(intent.getStringExtra(EXTRA_PASSWORD));

            // numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1)); // 1 is defualt value in case the extra is missing
        } else {
            setTitle("Add Student");
        }
    }

    public void addElements(CollegeAdapter collegeAdapter, ArrayList<String> collegesList) {
        for (int i = 0; i < collegeAdapter.getItemCount(); i++) {
            collegesList.add(collegeAdapter.getCollegeAt(i).getCollegeName());
        }
        collegeSpinner.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    private void saveNote() {
        String name = editTextName.getText().toString();
        String GPA = editTextCGPA.getText().toString();
        String college = "";
        if (collegeSpinner == null) {
            collegeSpinner.setSelection(0);
        }
        college = collegeSpinner.getSelectedItem().toString().trim();
        String gender = editTextGender.getText().toString();
        String password = editTextPassword.getText().toString();
        if (name.trim().isEmpty() || GPA.trim().isEmpty() || college.trim().isEmpty() || gender.trim().isEmpty() || password.trim().isEmpty()) {
            Toast.makeText(this, "Please fill all data", Toast.LENGTH_SHORT).show();
            return;
        }


        //   int priority = data.getIntExtra(AddEditNoteActivity.EXTRA_PRIORITY, 1); // 1 is just incase the integer is missing so it's 1 by default
        Student student = new Student(name, password, GPA, college, gender);

        studentViewModel.insert(student);
        finish(); // to close this activity
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }


}