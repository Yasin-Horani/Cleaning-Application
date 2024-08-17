package com.example.cleaningbuddy;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleaningbuddy.models.Room;
import com.example.cleaningbuddy.models.Task;

import java.util.List;

public class OverzichtKamersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_overzicht_kamers);

        // RecyclerView
        Context context = this;
        List<Room> rooms = Room.getAll(context);
        RecyclerView recyclerView = findViewById(R.id.overzicht_kamer_recyclerView_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TasksAdapterOverviewRoomsRecyclerView adapter = new TasksAdapterOverviewRoomsRecyclerView(context,rooms);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.overzicht_room_close_btn_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}