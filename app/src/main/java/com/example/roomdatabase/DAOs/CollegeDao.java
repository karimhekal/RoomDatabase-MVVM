package com.example.roomdatabase.DAOs;

import com.example.roomdatabase.Model.College;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CollegeDao {
    @Insert
    void insert(College college);

    @Delete
    void delete(College college);

    @Query("SELECT * FROM college_table")
    LiveData<List<College>> getAllColleges();    // live data means that data are automatically updated if it changes

}
