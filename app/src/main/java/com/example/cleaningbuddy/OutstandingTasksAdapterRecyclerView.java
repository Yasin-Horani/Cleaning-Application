package com.example.cleaningbuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cleaningbuddy.models.Room;
import com.example.cleaningbuddy.models.Task;
import com.example.cleaningbuddy.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OutstandingTasksAdapterRecyclerView extends RecyclerView.Adapter<OutstandingTasksAdapterRecyclerView.TaskViewHolder> {
    private List<Task> taskList;
    private Context context;

    public OutstandingTasksAdapterRecyclerView(Context context, List<Task> taskList) {
        this.context = context;
        setTaskList(taskList);
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = filterTasksWithStatusZero(taskList);
        notifyDataSetChanged();
    }

    private List<Task> filterTasksWithStatusZero(List<Task> tasks) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getStatus() == 0) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_outstanding_task, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.taskName.setText(task.getNaam());
        User user = User.getUser(context, task.getUserId());
        holder.taskUsername.setText(user.getGebruikersnaam());
        Room room = Room.getRoomById(context, task.getRoomId());
        holder.taskRoomName.setText(room.getName());

        boolean isActive = task.getStatus() == 1;
        holder.taskCheckbox.setChecked(isActive);
        holder.taskCheckbox.setOnCheckedChangeListener(null);

        holder.taskCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (buttonView.isPressed()) {
                task.setStatus(isChecked ? 1 : 0);
                Task.update(context, task);
                setTaskList(Task.getAll(context));
                notifyDataSetChanged();

                if (isChecked) {
                    task.setAfgerond(new Date());
                    Task.update(context, task);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskUsername;
        TextView taskRoomName;
        TextView taskName;
        CheckBox taskCheckbox;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskCheckbox = itemView.findViewById(R.id.task_status);
            taskUsername = itemView.findViewById(R.id.task_username);
            taskRoomName = itemView.findViewById(R.id.task_roomname);
            taskName = itemView.findViewById(R.id.task_name);
        }
    }
}