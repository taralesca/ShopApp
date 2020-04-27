package com.example.shopapp.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
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
    //    FavoritesFragment favorites;
    SellFragment sell;
    FeaturedFragment featured;
    private BottomNavigationView mainNav;
    private FrameLayout mainFrame;
//    AccountFragment account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        mainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mainNav = (BottomNavigationView) findViewById(R.id.main_nav);
        home = new HomeFragment();
//        favorites=new FavoritesFragment();
        sell = new SellFragment();
        featured = new FeaturedFragment();
//        account= new AccountFragment();
        setFragment(home);
        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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
//                    case R.id.it_acc:
//                        setFragment(account);
//                        return true;
                    default:
                        return false;
                }
            }
        });

    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();


    }
}
