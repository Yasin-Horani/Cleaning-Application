package com.example.cleaningbuddy;
import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cleaningbuddy.models.Task;

import java.text.BreakIterator;
import java.util.List;

public class EditingTasksActivityAdapterRecyclerView extends RecyclerView.Adapter<EditingTasksActivityAdapterRecyclerView.EditTasksViewHolder> {
    private List<Task> tasksList;
    private Context context;

    public EditingTasksActivityAdapterRecyclerView(List<Task> tasksList, Context context) {
        this.tasksList = tasksList;
        this.context = context;
    }

    @NonNull
    @Override
    public EditTasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_editing_task, parent, false);
        return new EditTasksViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EditingTasksActivityAdapterRecyclerView.EditTasksViewHolder holder, int position) {
        Task task = tasksList.get(position);
        holder.task_edit_id.setText(String.valueOf(task.getId())); // Assuming id is an integer
        holder.task_edit_task_name.setText(task.getNaam());

        holder.editTasks_delete_btn_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task.delete(context, task);
                tasksList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, tasksList.size());
            }
        });
        holder.editTasks_update_btn_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("EditingTasksAdapter", "Navigating to EditOneTaskActivity with TASK_ID: " + task.getId());
                Intent intent = new Intent(context, EditOneTaskActivity.class);
                intent.putExtra("TASK_ID", task.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasksList.size();
    }

    public static class EditTasksViewHolder extends RecyclerView.ViewHolder {
        TextView task_edit_id;
        TextView task_edit_task_name;
        Button editTasks_update_btn_id;
        Button editTasks_delete_btn_id;

        public EditTasksViewHolder(@NonNull View itemView) {
            super(itemView);
            task_edit_id = itemView.findViewById(R.id.task_edit_id);
            task_edit_task_name = itemView.findViewById(R.id.task_edit_task_name);
            editTasks_update_btn_id = itemView.findViewById(R.id.editTasks_update_btn_id);
            editTasks_delete_btn_id = itemView.findViewById(R.id.editTasks_delete_btn_id);
        }
    }
}