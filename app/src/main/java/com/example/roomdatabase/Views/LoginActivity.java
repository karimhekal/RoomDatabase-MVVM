package com.example.roomdatabase.Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomdatabase.Adapter.StudentAdapter;
import com.example.roomdatabase.Model.Student;
import com.example.roomdatabase.ViewModels.StudentViewModel;
import com.example.roomdatabase.R;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class LoginActivity extends AppCompatActivity {
    Button login;
    EditText user, pass;
    StudentViewModel studentViewModel;
    Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        user = findViewById(R.id.user);
        pass = findViewById(R.id.password);
        final StudentAdapter studentAdapter = new StudentAdapter();

        login = findViewById(R.id.loginBtn);
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);

        studentViewModel.getAllStudents().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                //this is what happens when when data changes // the code bellow should be related to changing the view so it displays the d
                studentAdapter.setStudents(students);
                //Toast.makeText(context, "onChanged", Toast.LENGTH_SHORT).show(); // this toast was to make sure that the live data is changed
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                if (user.getText().toString().equals("admin") && pass.getText().toString().equals("admin")) {
                    intent.putExtra("USERNAME_EXTRA", user.getText().toString());
                    startActivity(intent);

                } else {
                    for (int i = 0; i < studentAdapter.getItemCount(); i++) { // accessing the data in studentAdapter
                        if ((studentAdapter.getStudentAt(i).getName().equals(user.getText().toString())) && (studentAdapter.getStudentAt(i).getPassword().equals(pass.getText().toString()))) {
                            Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show();
                            intent.putExtra("USERNAME_EXTRA", user.getText().toString());
                            startActivity(intent);
                            break; // get out of the loop
                        } else {
                            Toast.makeText(context, "name or password is invalid", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });
    }

}
