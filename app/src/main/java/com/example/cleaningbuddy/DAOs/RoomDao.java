package com.example.cleaningbuddy.DAOs;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cleaningbuddy.models.Task;

import java.util.List;
@Dao
public interface RoomDao {

    @Query("Select * FROM room WHERE id = :id")
    com.example.cleaningbuddy.models.Room getById(int id);

    @Query("SELECT * FROM room")
    List<com.example.cleaningbuddy.models.Room> getAll();

    @Insert
    void insert(com.example.cleaningbuddy.models.Room room);
    @Update
    void update(com.example.cleaningbuddy.models.Room room);
    @Delete
    void delete(com.example.cleaningbuddy.models.Room room);

    @Query("SELECT * FROM task WHERE roomId = :id")
    List<Task> getTasksByRoomId(int id);
}
