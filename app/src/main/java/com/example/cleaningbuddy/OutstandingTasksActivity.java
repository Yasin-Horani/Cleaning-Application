package com.example.cleaningbuddy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleaningbuddy.models.Task;

import java.util.List;

public class OutstandingTasksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_outstanding_tasks);

        findViewById(R.id.OutstandingTasks_ShowCompletedTasks_btn_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OutstandingTasksActivity.this, CompletedTasksActivity.class);
                startActivity(intent);
            }
        });

        Context context = this;
        List<Task> tasks = Task.getAll(context);
        RecyclerView recyclerView = findViewById(R.id.OutstandingTasks_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        OutstandingTasksAdapterRecyclerView adapter = new OutstandingTasksAdapterRecyclerView(context, tasks);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.OutstandingTasks_ShowCompletedTasks_btn_id).setOnClickListener(v -> {
            startActivity(new Intent(OutstandingTasksActivity.this, CompletedTasksActivity.class));
        });

        findViewById(R.id.OutstandingTasks_TasksAdjustData_btn_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OutstandingTasksActivity.this, EditingTasksActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.OutstandingTasks_close_btn_text).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(OutstandingTasksActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
