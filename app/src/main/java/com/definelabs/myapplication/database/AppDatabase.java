package com.definelabs.myapplication.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Match.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "MatchDatabase";

    public abstract MatchDao matchDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {

        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    //Asynchronous dead in android for this add the statement
                    INSTANCE = Room.databaseBuilder(context, AppDatabase.class,
                            DATABASE_NAME)
                            .allowMainThreadQueries()//to avoid the Asynctask
                            .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // database callback to create a single instance
    static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsynTask(INSTANCE);
        }
    };

    // before creating the data previous data is deleted
    static class PopulateAsynTask extends AsyncTask<Void, Void, Void> {
        private MatchDao matchDao;

        PopulateAsynTask(AppDatabase appDatabase) {

            matchDao = appDatabase.matchDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            matchDao.deleteAll();
            return null;
        }
    }

}
