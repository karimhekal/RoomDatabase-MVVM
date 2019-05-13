package com.example.roomdatabase.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "students_table")
public class Student {
    @PrimaryKey(autoGenerate = true) // id is primary key and auto incremented
    private int id;


    private String name, password, cgpa, college, gender;

    public Student(String name, String password, String cgpa, String college, String gender) {
        this.name = name;
        this.password = password;
        this.cgpa = cgpa;
        this.college = college;
        this.gender = gender;
    }


    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getCgpa() {
        return cgpa;
    }

    public String getCollege() {
        return college;
    }

    public String getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
