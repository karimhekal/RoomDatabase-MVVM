package com.example.roomdatabase.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.roomdatabase.DAOs.NoteDao;
import com.example.roomdatabase.Model.Student;
import com.example.roomdatabase.Databases.NoteDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Student>> allNotes;
    private List<Student> checkStudents;

    public NoteRepository(Application application) // since application is subclass of context so we will pass it
    {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNodes();

    }




    public void insert(Student student) {
        new InsertNoteAsynTask(noteDao).execute(student);
    }

    public void update(Student student) {
        new UpdateNoteAsynTask(noteDao).execute(student);

    }

    public void delete(Student student) {
        new DeleteNoteAsynTask(noteDao).execute(student);
    }

    public void deleteAllNotes() {
        new DeleteAllNoteAsynTask(noteDao).execute();
    }

    public LiveData<List<Student>> getAllNotes() { //we'll have to run this in the background as a thread we will use async task to multi thread
        return allNotes;
    }



    // public List<Student> check() { return checkStudents; } //we'll have to run this in the background as a thread we will use async task to multi thread


    private static class UpdateNoteAsynTask extends AsyncTask<Student, Void, Void> {

        private NoteDao noteDao; // we need this notedao to make database operations

        private UpdateNoteAsynTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            noteDao.update(students[0]);
            return null;

        }
    }

    private static class DeleteNoteAsynTask extends AsyncTask<Student, Void, Void> {

        private NoteDao noteDao; // we need this notedao to make database operations

        private DeleteNoteAsynTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Student... students) {

            noteDao.delete(students[0]);

            return null;

        }
    }

    private static class InsertNoteAsynTask extends AsyncTask<Student, Void, Void> {

        private NoteDao noteDao; // we need this notedao to make database operations

        private InsertNoteAsynTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            noteDao.insert(students[0]);
            return null;
        }
    }



    private static class DeleteAllNoteAsynTask extends AsyncTask<Student, Void, Void> {

        private NoteDao noteDao; // we need this notedao to make database operations

        private DeleteAllNoteAsynTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            noteDao.deleteAllNodes();
            return null;

        }
    }

}
