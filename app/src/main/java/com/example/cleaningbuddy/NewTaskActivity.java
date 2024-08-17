package com.example.cleaningbuddy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cleaningbuddy.models.Room;
import com.example.cleaningbuddy.models.Task;
import com.example.cleaningbuddy.models.User;

import android.app.DatePickerDialog;
import android.widget.DatePicker;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import java.util.Locale;

public class NewTaskActivity extends AppCompatActivity {

    private List<Room> roomList;
    private List<User> userList;
    private Calendar calendar;
    EditText editTextDate;
    Date startDatum;
    String intervalSelectedItem;

    Room room;
    User user;
    private String taskDescription = "";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        Context context = this;


        // Room
        Spinner roomsSpinner = findViewById(R.id.editOneTask_roomName_sp_id);
        roomList = Room.getAll(context);
        ArrayAdapter<Room> roomAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roomList);
        roomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomsSpinner.setAdapter(roomAdapter);
        roomsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                room = (Room) roomsSpinner.getSelectedItem();

                Toast.makeText(NewTaskActivity.this, "Geselecteerde Kamer: " + room.getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // User
        Spinner usersSpinner = findViewById(R.id.editOneTask_Assign_sp_id);
        userList = User.getAll(context);
        ArrayAdapter<User> userAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, userList);
        userAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        usersSpinner.setAdapter(userAdapter);
        usersSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                user = (User) usersSpinner.getSelectedItem();

                Toast.makeText(NewTaskActivity.this, "Geselecteerde Kamer: " + user.getGebruikersnaam(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Interval
        Spinner intervalSpinner = findViewById(R.id.editOneTask_Interval_sp_id);
        List<String> intervalOptions = Arrays.asList("Dagelijks", "Wekelijks", "Maandelijks");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, intervalOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        intervalSpinner.setAdapter(adapter);
        intervalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                intervalSelectedItem = (String) intervalSpinner.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // Date
        editTextDate = findViewById(R.id.editOneTask_startDate_dt_id);
         calendar = Calendar.getInstance();
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        //Description Beschrijven
        EditText taskDescriptionEditText = findViewById(R.id.nieuwe_taak_beschrijven_et_id);
        taskDescriptionEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                taskDescription = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        findViewById(R.id.editOneTask_updateOneTask_btn_id).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText nameET = findViewById(R.id.editOneTask_taskName_pt_id);
                String name = nameET.getText().toString();

                Task task = new Task(name,startDatum, null, 0, "eenheid",intervalSelectedItem, room.getId(), user.getId());
                task.setBeschrijving(taskDescription);
                task.add(context, task);
                Intent intent = new Intent(NewTaskActivity.this, NewTaskActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.editOneTask_close_btn_id).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewTaskActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showDatePickerDialog() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {

                calendar.set(selectedYear, selectedMonth, selectedDay);
                startDatum = calendar.getTime();
                updateDateInEditText();
            }
        }, year, month, day);

        datePickerDialog.show();
    }

    private void updateDateInEditText() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        editTextDate.setText(dateFormat.format(calendar.getTime()));
    }


}