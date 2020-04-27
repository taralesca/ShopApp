package com.example.shopapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shopapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    public RegisterActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        final TextView emailField = findViewById(R.id.email_field);
        final TextView passwordField = findViewById(R.id.password_field);
        Button logInButton = findViewById(R.id.submit_register_button);

        logInButton.setOnClickListener(v ->
                createAccount(emailField.getText().toString(), passwordField.getText().toString())
        );

    }

    public void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, R.string.register_success,
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, OptionsActivity.class);
                        startActivity(intent);
                        SharedPreferences settings = getSharedPreferences("Login", 0);
                        settings.edit().putString("user", email).apply();
                        settings.edit().putString("password", password).apply();
                    } else {
                        Toast.makeText(RegisterActivity.this, R.string.register_failed,
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
