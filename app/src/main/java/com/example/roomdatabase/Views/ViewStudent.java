package com.example.roomdatabase.Views;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.roomdatabase.Adapter.StudentAdapter;
import com.example.roomdatabase.Model.Student;
import com.example.roomdatabase.ViewModels.StudentViewModel;
import com.example.roomdatabase.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ViewStudent extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1; // it's important to distinguish between these two codes
    public static final int EDIT_NOTE_REQUEST = 2; // to indicate to the AddEdit class that we're editing
    Context context;
    private StudentViewModel studentViewModel;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String name = data.getStringExtra(AddEditNoteActivity.EXTRA_NAME);
            String CGPA = data.getStringExtra(AddEditNoteActivity.EXTRA_CGPA);
            String college = data.getStringExtra(AddEditNoteActivity.EXTRA_COLLEGE);
            String gender = data.getStringExtra(AddEditNoteActivity.EXTRA_GENDER);
            String password = data.getStringExtra(AddEditNoteActivity.EXTRA_PASSWORD);
            //   int priority = data.getIntExtra(AddEditNoteActivity.EXTRA_PRIORITY, 1); // 1 is just incase the integer is missing so it's 1 by default
            Student student = new Student(name, password, CGPA, college, gender);
            studentViewModel.insert(student);
            Toast.makeText(context, "student saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) { // if we're editing student
            int id = data.getIntExtra(AddEditNoteActivity.EXTRA_ID, -1);
            if (id == -1) { // this means something went wrong
                Toast.makeText(context, "Student Can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String name = data.getStringExtra(AddEditNoteActivity.EXTRA_NAME);
            String CGPA = data.getStringExtra(AddEditNoteActivity.EXTRA_CGPA);
            String college = data.getStringExtra(AddEditNoteActivity.EXTRA_COLLEGE);
            String gender = data.getStringExtra(AddEditNoteActivity.EXTRA_GENDER);
            String password = data.getStringExtra(AddEditNoteActivity.EXTRA_PASSWORD);
            //   int priority = data.getIntExtra(AddEditNoteActivity.EXTRA_PRIORITY, 1); // 1 is just incase the integer is missing so it's 1 by default
            Student student = new Student(name, password, CGPA, college, gender);
            student.setId(id); // to identify which student will be updated
            studentViewModel.update(student); // updating student
            Toast.makeText(context, "student updated", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(context, "Nothing was saved", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // inflating menu to top right of screen to delete all
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_notes:
                studentViewModel.deleteAllNotes();
                Toast.makeText(context, "All Notes Deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;



        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final StudentAdapter studentAdapter = new StudentAdapter();
        recyclerView.setAdapter(studentAdapter);

        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        studentViewModel.getAllNotes().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                //this is what happens when when data changes // the code bellow should be related to changing the view so it displays the d
                studentAdapter.setStudents(students);
                //Toast.makeText(context, "onChanged", Toast.LENGTH_SHORT).show(); // this toast was to make sure that the live data is changed
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) { //to support swiping right and left
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                studentViewModel.delete(studentAdapter.getStudentAt(viewHolder.getAdapterPosition()));
                Toast.makeText(context, "Student Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);



    }
}
