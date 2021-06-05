package com.example.sign;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class FormationFragment extends Fragment implements Adapter.clickListener {


    private final String URL_REQUEST;
    private RecyclerView recyclerView;
    private Adapter a;
    private ArrayList<Formation> list = new ArrayList<>();

    ProgressDialog progressDialog;

    public FormationFragment(ProgressDialog progressDialog, String tag) {
        URL_REQUEST = "https://hyrvin.pythonanywhere.com/api/list/" + tag + "/?format=json";
        setProgressDialog(progressDialog);
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

        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL_REQUEST, null,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject j = response.getJSONObject(i);
                            Formation f = new Formation();
                            f.setTitre(j.getString("title").trim());
                            f.setDescription(j.getString("description").trim());
                            f.setLieu(j.getString("lieu").trim());
                            f.setDate_d(j.getString("date_debut").trim());
                            f.setLien(j.getString("lien"));
                            list.add(f);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        a.notifyDataSetChanged();
                    }
                }, error -> {

        }
        );


        requestQueue.add(jsonArrayRequest);
        requestQueue.addRequestFinishedListener((RequestQueue.RequestFinishedListener<String>) request -> {
            if(progressDialog != null && progressDialog.isShowing())
                progressDialog.dismiss();
        });
    }

    @Override
    public void onClickListener(int position) {
        Uri u = Uri.parse(list.get(position).getLien());
        Intent i = new Intent(String.valueOf(FormActivity.class));
        startActivity(new Intent(Intent.ACTION_VIEW, u));
    }

    private void setProgressDialog(ProgressDialog progressDialog){
        this.progressDialog = progressDialog;
        this.progressDialog.show();
        this.progressDialog.setContentView(R.layout.progress);
        this.progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );
    }
}