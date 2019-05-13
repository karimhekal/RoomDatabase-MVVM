package com.example.roomdatabase.ViewModels;

import android.app.Application;

import com.example.roomdatabase.Model.College;
import com.example.roomdatabase.Repository.CollegeRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CollegeViewModel extends AndroidViewModel {

    private CollegeRepository repository;
    private LiveData<List<College>> allColleges;

    public CollegeViewModel(@NonNull Application application) {

        super(application);
        repository = new CollegeRepository(application);
        allColleges = repository.getAllColleges();

    }

    public void insert(College college) {
        repository.insert(college);
    }

    public void delete(College college) {
        repository.delete(college);
    }



    public LiveData<List<College>> getAllColleges() {
        return allColleges;
    }

}
