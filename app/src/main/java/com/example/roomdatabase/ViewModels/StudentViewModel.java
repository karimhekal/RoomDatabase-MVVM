package com.example.roomdatabase.ViewModels;

import android.app.Application;

import com.example.roomdatabase.Model.Student;
import com.example.roomdatabase.Repository.NoteRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class StudentViewModel extends AndroidViewModel {

    private NoteRepository repository;
    private LiveData<List<Student>> allNotes;

    public StudentViewModel(@NonNull Application application) {

        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();

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



    public void deleteAllNotes() {
        repository.deleteAllNotes();
    }


    public LiveData<List<Student>> getAllNotes() {
        return allNotes;
    }

}
