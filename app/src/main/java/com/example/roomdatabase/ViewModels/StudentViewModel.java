package com.example.roomdatabase.ViewModels;

import android.app.Application;

import com.example.roomdatabase.Model.Student;
import com.example.roomdatabase.Repository.StudentRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class StudentViewModel extends AndroidViewModel {

    private StudentRepository repository;
    private LiveData<List<Student>> allStudents;

    public StudentViewModel(@NonNull Application application) {

        super(application);
        repository = new StudentRepository(application);
        allStudents = repository.getAllStudents();

    }

    public void insert(Student student) {
        repository.insert(student);
    }

    public void update(Student student) {
        repository.update(student);
    }

    public void delete(Student student) {
        repository.delete(student);
    }



    public void deleteAllStudents() {
        repository.deleteAllStudents();
    }


    public LiveData<List<Student>> getAllStudents() {
        return allStudents;
    }

}
