package com.example.shopapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.shopapp.R;
import com.example.shopapp.fragments.FeaturedFragment;
import com.example.shopapp.fragments.HomeFragment;
import com.example.shopapp.fragments.SellFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OptionsActivity extends AppCompatActivity {

    HomeFragment home;
    SellFragment sell;
    FeaturedFragment featured;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        BottomNavigationView mainNav = findViewById(R.id.main_nav);
        home = new HomeFragment();
        sell = new SellFragment();
        featured = new FeaturedFragment();
        setFragment(home);
        mainNav.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.it_home:
                    setFragment(home);
                    return true;
                case R.id.it_featured:
                    setFragment(featured);
                    return true;
                case R.id.it_sell:
                    setFragment(sell);
                    return true;
                default:
                    return false;
            }
        });

    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}
