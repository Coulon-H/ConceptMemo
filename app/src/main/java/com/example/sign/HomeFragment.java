package com.example.sign;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment implements Adapter.clickListener{

    private final String URL_REQUEST = "https://hyrvin.pythonanywhere.com/api/list/news/?format=json";
    private RecyclerView recyclerView;
    private Adapter a;
    private ArrayList<Formation> list = new ArrayList<>();

    public HomeFragment() {
        a = new Adapter(list, R.layout.model_bloc, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_formation, container, false);

        recyclerView = v.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SpacingItem i = new SpacingItem(25);
        recyclerView.addItemDecoration(
                i
        );
        recyclerView.setAdapter(a);
        Retrieve();

        return v;
    }

    private void Retrieve() {// méthode d'enregistrement

        RequestQueue requestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL_REQUEST, null,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject j = response.getJSONObject(i);
                            Formation f = new Formation();
                            f.setTitre(j.getString("title").trim());
                            f.setDescription(j.getString("description").trim());
                            list.add(f);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //System.out.println(list);
                        //recyclerView.setAdapter(new Adapter(list, R.layout.model_bloc));
                        a.notifyDataSetChanged();
                    }
                }, error -> {

        }
        );


        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onClickListener(int position) {

    }
}