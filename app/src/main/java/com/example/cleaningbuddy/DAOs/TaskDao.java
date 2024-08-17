package com.example.cleaningbuddy.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cleaningbuddy.models.Room;
import com.example.cleaningbuddy.models.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("Select * FROM task WHERE id = :id")
    com.example.cleaningbuddy.models.Task getById(int id);

    @Query("SELECT * FROM task WHERE roomId = :id")
    List<Task> getTasksById(int id);

    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Insert
    void insert(com.example.cleaningbuddy.models.Task task);
    @Update
    void update(com.example.cleaningbuddy.models.Task task);
    @Delete
    void delete(com.example.cleaningbuddy.models.Task task);

}
