package com.example.cleaningbuddy.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.cleaningbuddy.Converters;

import java.io.Serializable;
import java.util.Date;

@Entity(/*foreignKeys = {
        @ForeignKey(entity = Room.class,
                parentColumns = "id",
                childColumns = "roomId"),
        @ForeignKey(entity = User.class,
                parentColumns = "id",
                childColumns = "userId")*/
        /*,
        @ForeignKey(entity = Task.class,
                parentColumns = "id",
                childColumns = "taskId")

}*/)
public class History implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    @TypeConverters({Converters.class})
    private Date date;

    @ColumnInfo
    @TypeConverters({Converters.class})
    private Date completedTasks;

    @ColumnInfo
    private Integer userId;

    @ColumnInfo
    private Integer taakId;


    @ColumnInfo
    private Integer roomId;

    // Constructor
    public History(Date date, Date completedTasks) {
        this.date = date;
        this.completedTasks = completedTasks;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(Date completedTasks) {
        this.completedTasks = completedTasks;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTaakId() {
        return taakId;
    }

    public void setTaakId(Integer taakId) {
        this.taakId = taakId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
}
