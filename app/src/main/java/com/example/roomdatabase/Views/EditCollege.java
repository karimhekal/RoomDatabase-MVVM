package com.example.roomdatabase.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.roomdatabase.Adapter.CollegeAdapter;
import com.example.roomdatabase.Model.College;
import com.example.roomdatabase.R;
import com.example.roomdatabase.ViewModels.CollegeViewModel;

import java.util.List;

public class EditCollege extends AppCompatActivity {
    Context context;
    EditText editTextCollege;
    Button insert;

    private CollegeViewModel collegeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_college);
        insert = (Button) findViewById(R.id.insert_college);
        editTextCollege = (EditText) findViewById(R.id.edit_text_college);
        RecyclerView recyclerView = findViewById(R.id.college_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final CollegeAdapter collegeAdapter = new CollegeAdapter();
        recyclerView.setAdapter(collegeAdapter);

        collegeViewModel = ViewModelProviders.of(this).get(CollegeViewModel.class);
        collegeViewModel.getAllColleges().observe(this, new Observer<List<College>>() {
            @Override
            public void onChanged(List<College> colleges) {
                //this is what happens when when data changes // the code bellow should be related to changing the view so it displays the d
                collegeAdapter.setColleges(colleges);
                //Toast.makeText(context, "onChanged", Toast.LENGTH_SHORT).show(); // this toast was to make sure that the live data is changed
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCollege();
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
                collegeViewModel.delete(collegeAdapter.getCollegeAt(viewHolder.getAdapterPosition()));
                Toast.makeText(EditCollege.this, "College Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

    }


    public void saveCollege() {
        String collegeName = editTextCollege.getText().toString();
        collegeName.trim();


        if (collegeName.trim().isEmpty()) {
            Toast.makeText(this, "empty value", Toast.LENGTH_SHORT).show();
        } else {
            College college = new College(collegeName);
            collegeViewModel.insert(college);
        }

        editTextCollege.setText("");
        //finish(); // to close this activity
    }
}