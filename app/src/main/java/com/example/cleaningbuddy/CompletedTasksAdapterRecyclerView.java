package com.example.cleaningbuddy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleaningbuddy.models.Task;
import com.example.cleaningbuddy.models.User;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class CompletedTasksAdapterRecyclerView extends RecyclerView.Adapter<CompletedTasksAdapterRecyclerView.CompletedTaskHolder> {

    private List<Task> taskList;
    private Context context;
    private CompletedTasksActivity activity;

    public CompletedTasksAdapterRecyclerView(Context context, List<Task> taskList, CompletedTasksActivity activity) {
        this.taskList = taskList;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CompletedTaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_completed_tasks, parent, false);
        return new CompletedTaskHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedTaskHolder holder, int position) {
        Task task = taskList.get(position);
        holder.taskName.setText(task.getNaam());

        User user = User.getUser(context, task.getUserId());
        holder.taskUsername.setText(user.getGebruikersnaam());

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
        String formattedDate = sdf.format(task.getAfgerond());
        holder.finishingDate.setText(formattedDate);

        holder.restoreButton.setOnClickListener(view -> {
            task.setStatus(0);
            Task.update(context, task);
            taskList.remove(position);
            notifyItemRemoved(position);
            activity.refreshTasks();
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public void updateTaskList(List<Task> newTaskList) {
        this.taskList = newTaskList;
        notifyDataSetChanged();
    }

    public static class CompletedTaskHolder extends RecyclerView.ViewHolder {
        TextView taskUsername;
        TextView taskName;
        TextView finishingDate;
        Button restoreButton;

        public CompletedTaskHolder(@NonNull View itemView) {
            super(itemView);
            taskUsername = itemView.findViewById(R.id.taskUserName);
            taskName = itemView.findViewById(R.id.taskName);
            finishingDate = itemView.findViewById(R.id.finshingDate);
            restoreButton = itemView.findViewById(R.id.restoreButton);
        }
    }
}