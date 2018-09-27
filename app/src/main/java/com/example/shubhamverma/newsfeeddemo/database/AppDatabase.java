package com.example.shubhamverma.newsfeeddemo.database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.shubhamverma.newsfeeddemo.model.NewsFeedModel;

@Database(entities = {NewsFeedModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract NewsFeedDao newsFeedDao();


    public static AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder
                    (context.getApplicationContext(), AppDatabase.class, "newsFeed-database")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

}
