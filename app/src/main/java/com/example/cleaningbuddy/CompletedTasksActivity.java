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

import java.util.ArrayList;
import java.util.List;

public class CompletedTasksActivity extends AppCompatActivity {

    private CompletedTasksAdapterRecyclerView adapter;
    private List<Task> tasks;
    private List<Task> completedTasks;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_completed_tasks);

        Context context = this;
        tasks = Task.getAll(context);
        completedTasks = filterCompletedTasks(tasks);

        recyclerView = findViewById(R.id.completedTasks_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CompletedTasksAdapterRecyclerView(context, completedTasks, this);
        recyclerView.setAdapter(adapter);


        findViewById(R.id.CompletedTasks_close_btn_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompletedTasksActivity.this, OutstandingTasksActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<Task> filterCompletedTasks(List<Task> tasks) {
        List<Task> completedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getStatus() == 1) {
                completedTasks.add(task);
            }
        }
        return completedTasks;
    }

    public void refreshTasks() {
        tasks = Task.getAll(this);
        completedTasks = filterCompletedTasks(tasks);
        adapter.updateTaskList(completedTasks);
    }
}