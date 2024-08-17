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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cleaningbuddy.models.Room;
import com.example.cleaningbuddy.models.User;

public class RegisterActivity extends AppCompatActivity {

    private AppDatabase db;
    private TextView foutMeldingUsernameText;
    private TextView foutMeldingPasswordText;
    private TextView passwordValidationtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        Context context = this;
        foutMeldingUsernameText = findViewById(R.id.id_txt_foutMelding_username_registeren);
        foutMeldingPasswordText = findViewById(R.id.id_txt_foutMelding_password_registeren);
        passwordValidationtext = findViewById(R.id.password_validation_txt_id);

        db = AppDatabase.getDatabase(getApplicationContext());

        findViewById(R.id.register_btn_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usernameET = findViewById(R.id.register_username_pt_id);
                String username = usernameET.getText().toString();

                EditText passwordET = findViewById(R.id.register_password_pt_id);
                String password = passwordET.getText().toString();

                EditText verifyPasswordET = findViewById(R.id.register_verify_password_pt_id);
                String verifyPassword = verifyPasswordET.getText().toString();

                User userdb = db.userDao().getUsername(username);
                if (userdb != null) {
                    foutMeldingUsernameText.setText("Deze gepruiker bestaal al.");
                    foutMeldingUsernameText.setTextColor(ContextCompat.getColor(RegisterActivity.this, android.R.color.holo_red_dark));

                }
                else if (!password_validation_controller(password)) {
                    passwordValidationtext.setText("Het wachtwoord moet minimaal 8 cijfers of latters");
                    passwordValidationtext.setTextColor(ContextCompat.getColor(RegisterActivity.this, android.R.color.holo_red_dark));
                    if(userdb == null)
                    {
                        foutMeldingUsernameText.setVisibility(View.GONE);
                    }
                }

                else if (!verifyPassword.equals(password)) {
                    foutMeldingUsernameText.setVisibility(View.GONE);
                    foutMeldingPasswordText.setText("De wachtworden zijn niet gelijk.");
                    foutMeldingPasswordText.setTextColor(ContextCompat.getColor(RegisterActivity.this, android.R.color.holo_red_dark));
                    if(userdb == null)
                    {
                        foutMeldingUsernameText.setVisibility(View.GONE);
                    }
                    if (password_validation_controller(password))
                    {
                        passwordValidationtext.setVisibility(View.GONE);
                    }
                } else {
                    User user = new User(username, password);
                    user.add(context, user);
                    foutMeldingPasswordText.setVisibility(View.GONE);
                    foutMeldingUsernameText.setVisibility(View.GONE);
                    passwordValidationtext.setVisibility(View.GONE);
                    Toast.makeText(RegisterActivity.this, "Nieuwe gebruiker opgeslaagd.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });


        findViewById(R.id.register_close_btn_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public boolean password_validation_controller(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        return password.matches("^(?=.*[a-zA-Z0-9]).{8,}$");

    }

}