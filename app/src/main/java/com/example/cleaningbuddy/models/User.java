package com.example.cleaningbuddy.models;

import android.content.Context;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.cleaningbuddy.AppDatabase;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "user")
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String gebruikersnaam;

    @ColumnInfo
    private String wachtwoord;

    public User( String gebruikersnaam, String wachtwoord) {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
    }

    public static List<User> getAll(Context context)
    {
        return AppDatabase.getDatabase(context).userDao().getAll();
    }

    // Getters en Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }


    public static void add(Context context, User user)
    {
        if (user != null)
        {
            AppDatabase.getDatabase(context).userDao().insert(user);
        }
    }

    public static User getUser(Context context, Integer id)
    {
        return AppDatabase.getDatabase(context).userDao().getById(id);
    }

    @Override
    public String toString() {
        return gebruikersnaam;
    }
}
