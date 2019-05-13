package com.example.roomdatabase.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.roomdatabase.DAOs.StudentDao;
import com.example.roomdatabase.Databases.StudentDatabase;
import com.example.roomdatabase.Model.Student;

import java.util.List;

import androidx.lifecycle.LiveData;

public class StudentRepository {
    private StudentDao studentDao;
    private LiveData<List<Student>> allStudents;
    private List<Student> checkStudents;

    public StudentRepository(Application application) // since application is subclass of context so we will pass it
    {
        StudentDatabase database = StudentDatabase.getInstance(application);
        studentDao = database.studentDao();
        allStudents = studentDao.getAllNodes();

    }




    public void insert(Student student) {
        new InsertStudentAsynTask(studentDao).execute(student);
    }

    public void update(Student student) {
        new UpdateStudentAsynTask(studentDao).execute(student);

    }

    public void delete(Student student) {
        new DeleteStudentAsynTask(studentDao).execute(student);
    }

    public void deleteAllStudents() {
        new DeleteAllStudentAsynTask(studentDao).execute();
    }

    public LiveData<List<Student>> getAllStudents() { //we'll have to run this in the background as a thread we will use async task to multi thread
        return allStudents;
    }



    // public List<Student> check() { return checkStudents; } //we'll have to run this in the background as a thread we will use async task to multi thread


    private static class UpdateStudentAsynTask extends AsyncTask<Student, Void, Void> {

        private StudentDao studentDao; // we need this studentDao to make database operations

        private UpdateStudentAsynTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.update(students[0]);
            return null;

        }
    }

    private static class DeleteStudentAsynTask extends AsyncTask<Student, Void, Void> {

        private StudentDao studentDao; // we need this studentDao to make database operations

        private DeleteStudentAsynTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {

            studentDao.delete(students[0]);

            return null;

        }
    }

    private static class InsertStudentAsynTask extends AsyncTask<Student, Void, Void> {

        private StudentDao studentDao; // we need this studentDao to make database operations

        private InsertStudentAsynTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.insert(students[0]);
            return null;
        }
    }



    private static class DeleteAllStudentAsynTask extends AsyncTask<Student, Void, Void> {

        private StudentDao studentDao; // we need this studentdao to make database operations

        private DeleteAllStudentAsynTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.deleteAllNodes();
            return null;

        }
    }

}
