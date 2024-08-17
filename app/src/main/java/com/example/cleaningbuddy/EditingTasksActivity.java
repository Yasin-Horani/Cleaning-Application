package com.example.cleaningbuddy;
import android.annotation.SuppressLint;
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




public class EditingTasksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editing_tasks);

        Context context = this;
        List<Task> tasks = Task.getAll(context);
        RecyclerView recyclerView = findViewById(R.id.task_edit_recyclerView_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        EditingTasksActivityAdapterRecyclerView adapter = new EditingTasksActivityAdapterRecyclerView(tasks, context);
        recyclerView.setAdapter(adapter);



        findViewById(R.id.editTasks_close_btn_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditingTasksActivity.this, OutstandingTasksActivity.class);
                startActivity(intent);
            }
        });
    }
}
