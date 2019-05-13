package com.example.roomdatabase.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.roomdatabase.DAOs.CollegeDao;
import com.example.roomdatabase.Databases.CollegeDatabase;
import com.example.roomdatabase.Model.College;

import java.util.List;

import androidx.lifecycle.LiveData;

public class CollegeRepository {
    private CollegeDao collegeDao;
    private LiveData<List<College>> allColleges;

    public CollegeRepository(Application application) // since application is subclass of context so we will pass it
    {
        CollegeDatabase database = CollegeDatabase.getInstance(application);
        collegeDao = database.collegeDao();
        allColleges = collegeDao.getAllColleges();
    }

    public void insert(College college) {
        new InsertCollegeAsynTask(collegeDao).execute(college);
    }

    public void delete(College college) {
        new DeleteCollegeAsynTask(collegeDao).execute(college);

    }
    public LiveData<List<College>> getAllColleges() { //we'll have to run this in the background as a thread we will use async task to multi thread
        return allColleges;
    }

    private static class DeleteCollegeAsynTask extends AsyncTask<College, Void, Void> {
        private CollegeDao collegeDao; // we need this collegeDao to make database operations
        private DeleteCollegeAsynTask(CollegeDao CollegeDao) {
            this.collegeDao = CollegeDao;
        }
        @Override
        protected Void doInBackground(College... college) {
            collegeDao.delete(college[0]);
            return null;

        }
    }

    private static class InsertCollegeAsynTask extends AsyncTask<College, Void, Void> {

        private CollegeDao collegeDao; // we need this collegeDao to make database operations

        private InsertCollegeAsynTask(CollegeDao collegeDao) {
            this.collegeDao = collegeDao;
        }

        @Override
        protected Void doInBackground(College... colleges) {
            collegeDao.insert(colleges[0]);
            return null;
        }
    }
}
