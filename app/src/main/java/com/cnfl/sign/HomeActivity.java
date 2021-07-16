package com.cnfl.sign;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    BottomNavigationView bottom;
    ArrayList<Formation> list;
    ProgressDialog progressDialog;
    static int i = 0;

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
                                    i=1;
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.layout_frame
                                                    ,new FormationFragment(progressDialog, "CNFL")).commit();
                                },
                                v -> {
                                    i=1;
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.layout_frame
                                                    ,new FormationFragment(progressDialog, "INCUBATEUR")).commit();
                                },
                                v -> {i=1;
                                    getSupportFragmentManager().beginTransaction()
                                            .replace(R.id.layout_frame
                                                    ,new FormationFragment(progressDialog, "CEF")).commit();
                                }
                        )).commit();

    }

    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        Fragment selectedFragment = null;
        switch (item.getItemId()){
            case R.id.home:
                selectedFragment = new HomeFragment(v -> {
                    i=1;
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.layout_frame
                                    ,new FormationFragment(progressDialog, "CNFL")).commit();
                },
                        v -> {
                            i=1;
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.layout_frame
                                            ,new FormationFragment(progressDialog, "INCUBATEUR")).commit();
                        },
                        v -> {
                            i=1;
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.layout_frame
                                            ,new FormationFragment(progressDialog, "CEF")).commit();
                        }
                );
                break;
        }

        assert selectedFragment != null;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_frame
                        ,selectedFragment).commit();

        return true;
    };

    @Override
    public void onClick(View v) {
        Fragment selectedFragment = null;
        i = 1;
        selectedFragment = new HomeFragment(v2 -> {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.layout_frame
                                    ,new FormationFragment(progressDialog, "CNFL")).commit();
                },
                        v2 -> {
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.layout_frame
                                            ,new FormationFragment(progressDialog, "INCUBATEUR")).commit();
                        },
                        v2 -> {
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.layout_frame
                                            ,new FormationFragment(progressDialog, "CEF")).commit();
                        }
                );

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_frame
                        ,selectedFragment).commit();
    }

    @Override
    public void onBackPressed() {
        if(i == 1){
            Fragment selectedFragment = null;
            i = 0;
            selectedFragment = new HomeFragment(v2 -> {
                i=1;
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.layout_frame
                                ,new FormationFragment(progressDialog, "CNFL")).commit();
            },
                    v2 -> {i=1;
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.layout_frame
                                        ,new FormationFragment(progressDialog, "INCUBATEUR")).commit();
                    },
                    v2 -> {i=1;
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.layout_frame
                                        ,new FormationFragment(progressDialog, "CEF")).commit();
                    }
            );

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.layout_frame
                            ,selectedFragment).commit();

        }else{
            super.onBackPressed();
        }
    }


}