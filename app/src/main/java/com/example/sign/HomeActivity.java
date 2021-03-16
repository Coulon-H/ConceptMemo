package com.example.sign;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private final String URL_REQUEST = "http://192.168.1.95:8000/api/list/?format=json";
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
                        ,new HomeFragment()).commit();

    }

    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = item -> {
        Fragment selectedFragment = null;


        switch (item.getItemId()){
            case R.id.home:
                selectedFragment = new HomeFragment();
                break;
            case R.id.formation:
                //Retrieve();
                selectedFragment = new FormationFragment(progressDialog);
                break;
            /*case R.id.presentation:
                selectedFragment = new AboutFragment();*/
        }

        assert selectedFragment != null;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_frame
                        ,selectedFragment).commit();

        return true;
    };

    private void Retrieve() {// méthode d'enregistrement

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL_REQUEST, null,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject j = response.getJSONObject(i);
                            Formation f = new Formation();
                            f.setTitre(j.getString("name").trim());
                            f.setDescription(j.getString("password").trim());
                            f.setDate_d(j.getString("date_debut").trim());
                            list.add(f);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, error -> {

                }
        );


        requestQueue.add(jsonArrayRequest);
    }



}