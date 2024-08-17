package com.example.cleaningbuddy;

import static com.example.cleaningbuddy.models.Room.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cleaningbuddy.models.Room;

import java.text.SimpleDateFormat;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class NewRoomActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_room);

        Context context = this;

        findViewById(R.id.main_newRoom_btn_id).setOnClickListener(new View.OnClickListener() {
            private TextView foutMeldingText;

            @Override
            public void onClick(View v) {
                EditText nameET = findViewById(R.id.newRoom_createRoom_pt_id);
                String name = nameET.getText().toString();
                foutMeldingText = findViewById(R.id.new_room_fouttxt_id);
                if (stringNotEmptyNoNumbers(name)) {
                    Room room = new Room(name);
                    room.add(context, room);
                    foutMeldingText.setVisibility(View.GONE);
                    Toast.makeText(NewRoomActivity.this, "Nieuwe kamer opgeslaagd.", Toast.LENGTH_SHORT).show();
                } else {
                    foutMeldingText.setText("Ongeldige kamer naam");
                    foutMeldingText.setTextColor(ContextCompat.getColor(NewRoomActivity.this, android.R.color.holo_red_dark));

                }
                Intent intent = new Intent(NewRoomActivity.this, NewRoomActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.newRoom_close_btn_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewRoomActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    public boolean stringNotEmptyNoNumbers(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.matches("[^0-9]*");
    }

}