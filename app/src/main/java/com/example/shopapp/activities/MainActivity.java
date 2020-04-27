package com.example.shopapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shopapp.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences("Login", 0);
        String username = settings.getString("user", "");
        String password = settings.getString("password", "");
        if (username.isEmpty() || password.isEmpty()) {
            Intent intent = new Intent(MainActivity.this, AuthActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(MainActivity.this, OptionsActivity.class);
            startActivity(intent);
        }


//        timer =new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(MainActivity.this, OptionsActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        },5000);
    }


}
