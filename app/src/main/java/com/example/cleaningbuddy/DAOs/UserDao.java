package com.example.cleaningbuddy.DAOs;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cleaningbuddy.models.Room;
import com.example.cleaningbuddy.models.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("Select * FROM user WHERE id = :id")
    com.example.cleaningbuddy.models.User getById(int id);

    @Query("SELECT * FROM user WHERE gebruikersnaam = :username AND wachtwoord = :password")
    User getUser(String username, String password);

    @Query("SELECT * FROM user WHERE gebruikersnaam = :username")
    User getUsername(String username);

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Insert
    void insert(com.example.cleaningbuddy.models.User user);
    @Update
    void update(com.example.cleaningbuddy.models.User user);
    @Delete
    void delete(com.example.cleaningbuddy.models.User user);
}
