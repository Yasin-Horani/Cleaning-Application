package com.example.cleaningbuddy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.cleaningbuddy.models.Room;
import com.example.cleaningbuddy.models.User;

public class LoginActivity extends AppCompatActivity {

    private AppDatabase db;
    private boolean correctAccount;
    private TextView foutMeldingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        Context context = this;
        foutMeldingText = findViewById(R.id.id_txt_foutMelding_inloggen);
        db = AppDatabase.getDatabase(getApplicationContext());

        findViewById(R.id.login_login_btn_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usernameET = findViewById(R.id.login_username_pt_id);
                String username = usernameET.getText().toString();

                EditText passwordET = findViewById(R.id.login_password_pt_id);
                String password = passwordET.getText().toString();

                User user = db.userDao().getUser(username, password);
                correctAccount =  user == null ? false : true;

                if (correctAccount)
                {
                    foutMeldingText.setVisibility(View.GONE);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Inloggen geslaagd", Toast.LENGTH_SHORT).show();

                }else
                {
                    foutMeldingText.setText("Ongeldige gebruikersnaam of wachtwoord");
                    Toast.makeText(LoginActivity.this, "Ongeldige gebruikersnaam of wachtwoord", Toast.LENGTH_SHORT).show();
                    foutMeldingText.setTextColor(ContextCompat.getColor(LoginActivity.this, android.R.color.holo_red_dark));
                }
            }
        });
    }

    public void goToRegisterActivity(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void goToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
