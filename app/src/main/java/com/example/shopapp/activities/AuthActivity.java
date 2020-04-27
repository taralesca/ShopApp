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

public class AuthActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();

        final TextView emailField = findViewById(R.id.email_field);
        final TextView passwordField = findViewById(R.id.password_field);
        Button logInButton = findViewById(R.id.login_button);
        Button registerButton = findViewById(R.id.register_button);

        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), RegisterActivity.class);
            startActivity(intent);
        });

        logInButton.setOnClickListener(v -> signIn(emailField.getText().toString(), passwordField.getText().toString()));
    }

    public void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(AuthActivity.this, OptionsActivity.class);
                        startActivity(intent);
                        SharedPreferences settings = getSharedPreferences("Login", 0);
                        settings.edit().putString("user", email).apply();
                        settings.edit().putString("password", password).apply();
                    } else {
                        Toast.makeText(AuthActivity.this, R.string.auth_failed,
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
