package com.example.cleaningbuddy.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.cleaningbuddy.models.History;
import java.util.List;
@Dao
public interface HistoryDao {
    @Query("Select * FROM history WHERE id = :id")
    com.example.cleaningbuddy.models.History getById(int id);

    @Query("SELECT * FROM history")
    List<History> getAll();

    @Insert
    void insert(com.example.cleaningbuddy.models.History history);
    @Update
    void update(com.example.cleaningbuddy.models.History history);
    @Delete
    void delete(com.example.cleaningbuddy.models.History history);
}
