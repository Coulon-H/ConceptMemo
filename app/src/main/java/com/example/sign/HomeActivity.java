package com.example.sign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private final String URL_REQUEST = "http://192.168.1.95:8000/api/list/?format=json";
    BottomNavigationView bottom;
    ArrayList<Formation> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        list = new ArrayList<>();
        bottom = findViewById(R.id.bottom);
        bottom.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_frame
                        ,new HomeFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;


            switch (item.getItemId()){
                case R.id.home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.formation:
                    Retrieve();
                    selectedFragment = new FormationFragment(list);
                    break;
                case R.id.presentation:
                    selectedFragment = new AboutFragment();
            }

            assert selectedFragment != null;
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.layout_frame
                            ,selectedFragment).commit();

            return true;
        }
    };

    private void Retrieve() {// méthode d'enregistrement

        StringRequest request = new StringRequest(Request.Method.GET, URL_REQUEST, response -> {
            try {
                JSONArray jsonObject = new JSONArray(response);
                for(int i = 0; i < jsonObject.length(); i++){
                    JSONObject j = jsonObject.getJSONObject(i);
                    list.add(new Formation(j.getString("name").trim(), j.getString("password").trim()));
                }

                System.out.println(list);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {

        }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }

}