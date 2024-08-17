package com.example.cleaningbuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleaningbuddy.models.Room;
import com.example.cleaningbuddy.models.Task;
import com.example.cleaningbuddy.models.User;

import java.util.List;
public class TasksAdapterOverviewRoomsRecyclerView extends RecyclerView.Adapter<TasksAdapterOverviewRoomsRecyclerView.RoomViewHolder> {
    private List<Room> roomList;
    private Context context;

    public TasksAdapterOverviewRoomsRecyclerView(Context context, List<Room> roomList) {
        this.roomList = roomList;
        this.context = context;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_overzicht_kamers, parent, false);
        return new RoomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = roomList.get(position);


            holder.Id.setText(room.getId().toString());
            holder.roomName.setText(room.getName());
            holder.aantalTasks.setText(String.valueOf(room.getTasks(context).size()));

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Room.delete(context, room);
                roomList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, roomList.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder {
        TextView Id;
        TextView roomName;
        TextView aantalTasks;
        Button deleteButton;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            Id = itemView.findViewById(R.id.room_overzicht_roomId);
            roomName = itemView.findViewById(R.id.room_overzicht_roomname);
            aantalTasks = itemView.findViewById(R.id.room_overzicht_roomtaskcount);
            deleteButton = itemView.findViewById(R.id.room_overzicht_delete_button);
        }
    }
}