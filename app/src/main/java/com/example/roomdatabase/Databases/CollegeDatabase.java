package com.example.roomdatabase.Databases;

import android.content.Context;
import android.os.AsyncTask;

import com.example.roomdatabase.DAOs.CollegeDao;
import com.example.roomdatabase.Model.College;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = College.class, version = 5, exportSchema = false)
public abstract class CollegeDatabase extends RoomDatabase {
    private static CollegeDatabase instance;
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() { // populating our database
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            new CollegeDatabase.PopulateDbAsynTask(instance).execute();
            super.onCreate(db);
        }
    };
    public static synchronized CollegeDatabase getInstance(Context context) // getter that gets the context of app
    { // we're making a singleton
        if (instance == null) // if there's no object created from this class
        {
            instance = Room.databaseBuilder(context.getApplicationContext(), CollegeDatabase.class, "college_table")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback) // when our instance is first created , it will it will execute the oncreate method that's in roomCallback
                    .build(); // create one and build it
        }
        return instance; // returning that singleton and now it's not null

    }
    public abstract CollegeDao collegeDao();

    private static class PopulateDbAsynTask extends AsyncTask<Void, Void, Void> { // we need to execute this aynstask in onCreate
        private CollegeDao collegeDao;

        private PopulateDbAsynTask(CollegeDatabase db) {
            collegeDao = db.collegeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) { // this will be executes once since its a singleton
            collegeDao.insert(new College("Engineering"));
            return null;
        }
    }
}
