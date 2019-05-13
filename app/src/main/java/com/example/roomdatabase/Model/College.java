package com.example.roomdatabase.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "college_table")
public class College {
    @PrimaryKey (autoGenerate = true)
    private int collegeID;

    private String collegeName;

    public College(String collegeName ) {
        this.collegeName = collegeName;
    }

    public int getCollegeID() {
        return collegeID;
    }

    public void setCollegeID(int collegeID) {
        this.collegeID = collegeID;
    }

    public String getCollegeName() {
        return collegeName;
    }
}
