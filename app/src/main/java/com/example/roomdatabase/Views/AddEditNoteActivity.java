package com.example.roomdatabase.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.roomdatabase.R;

import androidx.appcompat.app.AppCompatActivity;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_NAME = "com.example.roomdatabase.EXTRA_NAME";
    public static final String EXTRA_COLLEGE = "com.example.roomdatabase.EXTRA_COLLEGE";
    public static final String EXTRA_GENDER = "com.example.roomdatabase.EXTRA_GENDER";
    public static final String EXTRA_PASSWORD = "com.example.roomdatabase.EXTRA_PASSWORD";
    public static final String EXTRA_ID = "com.example.roomdatabase.EXTRA_ID";
    public static final String EXTRA_CGPA = "com.example.roomdatabase.EXTRA_CGPA";
    public static final String EXTRA_PRIORITY = "com.example.roomdatabase.EXTRA_PRIORITY";
    private EditText editTextName;
    private EditText editTextCGPA;
    private EditText editTextCollege, editTextGender, editTextPassword;
   // private NumberPicker numberPickerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        editTextName = findViewById(R.id.edit_text_name);
        editTextCGPA = findViewById(R.id.edit_text_cgpa);
        editTextCollege = findViewById(R.id.edit_text_college);
        editTextGender = findViewById(R.id.edit_text_gender);
        editTextPassword = findViewById(R.id.edit_text_password);


        // numberPickerPriority = findViewById(R.id.number_picker_priority);
//        numberPickerPriority.setMinValue(1);
//        numberPickerPriority.setMaxValue(10);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Student");
            editTextName.setText(intent.getStringExtra(EXTRA_NAME));
            editTextCGPA.setText(intent.getStringExtra(EXTRA_CGPA));
            editTextCollege.setText(intent.getStringExtra(EXTRA_COLLEGE));
            editTextGender.setText(intent.getStringExtra(EXTRA_GENDER));
            editTextPassword.setText(intent.getStringExtra(EXTRA_PASSWORD));

           // numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1)); // 1 is defualt value in case the extra is missing
        } else {
            setTitle("Add Student");
        }
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
        String college = editTextCollege.getText().toString();
        String gender = editTextGender.getText().toString();
        String password = editTextPassword.getText().toString();
      //  int priority = numberPickerPriority.getValue();

        if (name.trim().isEmpty() || GPA.trim().isEmpty()||college.trim().isEmpty()||gender.trim().isEmpty()||password.trim().isEmpty()) {
            Toast.makeText(this, "Please fill all data", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name); // passing data to the base activity
        data.putExtra(EXTRA_CGPA, GPA);
        data.putExtra(EXTRA_COLLEGE, college);
        data.putExtra(EXTRA_PASSWORD, password);
        data.putExtra(EXTRA_GENDER, gender);


        int id = getIntent().getIntExtra(EXTRA_ID, -1); // whenever this value is -1 , the id is invalid
        if (id != -1) {// means there's data and we're editing note not Adding one
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data); // to make sure everything is okay and send it to main activity

        finish(); // to close this activity
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }
}
