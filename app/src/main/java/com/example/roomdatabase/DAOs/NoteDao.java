package com.example.roomdatabase.DAOs;

import com.example.roomdatabase.Model.Student;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NoteDao {
    @Insert
    void insert(Student student);

    @Update
    void update(Student student);

    @Delete
    void delete(Student student);

    @Query("DELETE FROM students_table")
    void deleteAllNodes();

    @Query("SELECT * FROM students_table ORDER BY id DESC ")
    LiveData<List<Student>> getAllNodes();    // live data means that data are automatically updated if it changes






}
