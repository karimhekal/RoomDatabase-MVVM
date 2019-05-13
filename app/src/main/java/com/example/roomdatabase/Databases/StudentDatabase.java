package com.example.roomdatabase.Databases;

import android.content.Context;
import android.os.AsyncTask;

import com.example.roomdatabase.DAOs.StudentDao;
import com.example.roomdatabase.Model.Student;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Student.class, version = 6,exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {

    private static StudentDatabase instance;
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() { // populating our database
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            new PopulateDbAsynTask(instance).execute();
            super.onCreate(db);
        }
    };

    public static synchronized StudentDatabase getInstance(Context context) // getter that gets the context of app
    { // we're making a singleton
        if (instance == null) // if there's no object created from this class
        {
            instance = Room.databaseBuilder(context.getApplicationContext(), StudentDatabase.class, "employee_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback) // when our instance is first created , it will it will execute the oncreate method that's in roomCallback
                    .build(); // create one and build it
        }
        return instance; // returning that singleton and now it's not null

    }

    // room subclasses our abstract class
    public abstract StudentDao studentDao();

    private static class PopulateDbAsynTask extends AsyncTask<Void, Void, Void> { // we need to execute this aynstask in onCreate
        private StudentDao studentDao;

        private PopulateDbAsynTask(StudentDatabase db) {
            studentDao = db.studentDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            studentDao.insert(new Student("mohamed", "karim", "3.3", "IT", "male"));
            studentDao.insert(new Student("ahmed", "ahmed", "3.2", "IT", "male"));
            studentDao.insert(new Student("emad", "emad", "2.3", "IT", "male"));

            return null;
        }
    }
}
