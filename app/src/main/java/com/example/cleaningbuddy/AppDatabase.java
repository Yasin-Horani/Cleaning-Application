package com.example.cleaningbuddy;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;

import com.example.cleaningbuddy.DAOs.HistoryDao;
import com.example.cleaningbuddy.DAOs.RoomDao;
import com.example.cleaningbuddy.DAOs.TaskDao;
import com.example.cleaningbuddy.DAOs.UserDao;
import com.example.cleaningbuddy.models.History;
import com.example.cleaningbuddy.models.User;


@Database(entities = {com.example.cleaningbuddy.models.Room.class, com.example.cleaningbuddy.models.Task.class, User.class, History.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RoomDao roomDao();

    public abstract UserDao userDao();

    public abstract TaskDao taskDao();

    public abstract HistoryDao historyDao();

    private static volatile AppDatabase database;

    public static AppDatabase getDatabase(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context, AppDatabase.class, "cleaningbuddy_database")
                    .allowMainThreadQueries().build();
        }
        return database;
    }
}