package com.example.sign;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle; 
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottom;
    ArrayList<Formation> list;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        list = new ArrayList<>();
        bottom = findViewById(R.id.bottom);
        progressDialog = new ProgressDialog(HomeActivity.this);

        bottom.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_frame
                        ,new HomeFragment(
                                v -> {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.layout_frame
                                                    ,new FormationFragment(progressDialog, "CNFL")).commit();
                                },
                                v -> {
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.layout_frame
                                                    ,new FormationFragment(progressDialog, "AUF")).commit();
                                }
                        )).commit();

    }

    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        Fragment selectedFragment = null;


        switch (item.getItemId()){
            case R.id.home:
                selectedFragment = new HomeFragment(v -> {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.layout_frame
                                    ,new FormationFragment(progressDialog, "CNFL")).commit();
                },
                        v -> {
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.layout_frame
                                            ,new FormationFragment(progressDialog, "AUF")).commit();
                        });
                break;
        }

        assert selectedFragment != null;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_frame
                        ,selectedFragment).commit();

        return true;
    };

}