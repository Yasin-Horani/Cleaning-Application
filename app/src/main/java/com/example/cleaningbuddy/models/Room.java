package com.example.cleaningbuddy.models;

import android.content.Context;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.cleaningbuddy.AppDatabase;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo
    private String name;

    // Constructor
    public Room(String name) {
        this.name = name;
    }

    // Getters en Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Database operaties
    public static List<Room> getAll(Context context) {
        return AppDatabase.getDatabase(context).roomDao().getAll();
    }

    public static void add(Context context, Room room) {
        if (room != null) {
            AppDatabase.getDatabase(context).roomDao().insert(room);
        }
    }

    public static Room getRoomById(Context context, Integer roomId) {

        return AppDatabase.getDatabase(context).roomDao().getById(roomId);
    }

    public static void delete(Context context, Room room) {
        if (room != null) {
            AppDatabase.getDatabase(context).roomDao().delete(room);
        }
    }

    public static void update(Context context, Room room) {
        if (room != null) {
            AppDatabase.getDatabase(context).roomDao().update(room);
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public List<Task> getTasks(Context context) {
        return AppDatabase.getDatabase(context).roomDao().getTasksByRoomId(id);
    }
}
