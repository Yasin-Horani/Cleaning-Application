package com.example.cleaningbuddy.models;

import android.content.Context;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.cleaningbuddy.AppDatabase;
import com.example.cleaningbuddy.Converters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(foreignKeys = {
        @ForeignKey(entity = Room.class,
                parentColumns = "id",
                childColumns = "roomId",
                onDelete = ForeignKey.NO_ACTION),
        @ForeignKey(entity = User.class,
                parentColumns = "id",
                childColumns = "userId",
                onDelete = ForeignKey.NO_ACTION)})
public class Task {

    // Constructor
    public Task(String naam, Date datum, Date afgerond, int status, String eenheid, String frequentie, Integer roomId, Integer userId) {
        this.naam = naam;

        this.datum = datum;
        this.afgerond = afgerond;
        this.status = status;
        this.eenheid = eenheid;
        this.frequentie = frequentie;
        this.roomId = roomId;
        this.userId = userId;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String naam;

    @ColumnInfo
    @TypeConverters({Converters.class})
    private Date datum;

    @ColumnInfo
    @TypeConverters({Converters.class})
    private Date afgerond;

    @ColumnInfo
    private int status;

    @ColumnInfo
    private String eenheid;

    @ColumnInfo
    private String frequentie;

    @ColumnInfo
    private String beschrijving;

    @ColumnInfo
    private Integer roomId;

    @ColumnInfo
    private Integer userId;

    // Getters en Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }
    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Date getAfgerond() {
        return afgerond;
    }

    public void setAfgerond(Date afgerond) {
        this.afgerond = afgerond;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEenheid() {
        return eenheid;
    }

    public void setEenheid(String eenheid) {
        this.eenheid = eenheid;
    }

    public String getFrequentie() {
        return frequentie;
    }

    public void setFrequentie(String frequentie) {
        this.frequentie = frequentie;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    // Database operaties
    public static void add(Context context, Task task) {
        if (task != null) {
            AppDatabase.getDatabase(context).taskDao().insert(task);
        }
    }

    public static List<Task> getAll(Context context) {
        return AppDatabase.getDatabase(context).taskDao().getAll();
    }

    public static void delete(Context context, Task task) {
        if (task != null) {
            AppDatabase.getDatabase(context).taskDao().delete(task);
        }
    }

    public static void update(Context context, Task task) {
        if (task != null) {
            AppDatabase.getDatabase(context).taskDao().update(task);
        }
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }


    @Override
    public String toString() {
        return naam;
    }

    public List<Task> getTasksID(Context context) {
        return AppDatabase.getDatabase(context).taskDao().getTasksById(id);
    }

    public static Task getById(Context context, int id) {
        return AppDatabase.getDatabase(context).taskDao().getById(id);
    }
}
