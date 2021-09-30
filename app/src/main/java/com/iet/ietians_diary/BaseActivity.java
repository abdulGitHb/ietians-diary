package com.iet.ietians_diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BaseActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.container, new DashboardFragment());
        fragmentTransaction.commit();


        ColorStateList iconColorStates = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_checked},
                        new int[]{android.R.attr.state_checked}
                },
                new int[]{
                        getResources().getColor(R.color.text_black_200),
                        getResources().getColor(R.color.text_black_400)
                });

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setItemIconTintList(iconColorStates);
        bottomNavigationView.setItemTextColor(iconColorStates);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        getFragment(new DashboardFragment());
                        bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);
                        break;
                    case R.id.search:
                        bottomNavigationView.getMenu().findItem(R.id.search).setChecked(true);
                        getFragment(new DashboardFragment());
                        break;
                    case R.id.add:
                        bottomNavigationView.getMenu().findItem(R.id.add).setChecked(true);
                        getFragment(new DashboardFragment());
                        break;
                    case R.id.favorite:
                        bottomNavigationView.getMenu().findItem(R.id.favorite).setChecked(true);
                        getFragment(new RoadmapFragment());
                        break;
                    case R.id.account:
                        bottomNavigationView.getMenu().findItem(R.id.account).setChecked(true);
                        getFragment(new DashboardFragment());
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                return;
            }
        });

    }

    private void getFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

}