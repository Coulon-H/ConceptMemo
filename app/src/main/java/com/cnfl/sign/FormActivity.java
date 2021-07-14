package com.cnfl.sign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FormActivity extends AppCompatActivity {

    TextView Title, Description;
    EditText Name, Surname, Email, Structure;
    Button Sign;
    static boolean check = false;
    int id;

    private final String URL_REQUEST = "https://jazzs.pythonanywhere.com/api/list/signup/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);



        Title = findViewById(R.id.Title);
        Description = findViewById(R.id.Description);
        Name = findViewById(R.id.Name);
        Surname = findViewById(R.id.Surname);
        Email = findViewById(R.id.Email);
        Structure = findViewById(R.id.Structure);
        Sign = findViewById(R.id.Signin);

        checkerIntent();
        Sign.setOnClickListener(v -> verify("Remplissez le champ"));




    }

    private void checkerIntent(){
        if(getIntent().hasExtra("Title")){
            Intent i = getIntent();
            Title.setText(i.getStringExtra("Title"));
            id = i.getIntExtra("id", 0);
            Description.setText(i.getStringExtra("description"));
        }
    }

    private void verify(String error){
        if(!check) {
            if (Name.getText().toString().isEmpty()) {
                Name.setError(error);
            }
            if (Surname.getText().toString().isEmpty()) {
                Surname.setError(error);
            }
            if (Email.getText().toString().isEmpty()) {
                Email.setError(error);
            }
            if (Structure.getText().toString().isEmpty()) {
                Structure.setError(error);
            }
        }

        check = true;
        if (!Email.getText().toString().isEmpty() && !Email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+")) {
            Email.setError("Adresse Email Incorrect");
        }

        if(!Name.getText().toString().isEmpty() && !Surname.getText().toString().isEmpty()
                && !Email.getText().toString().isEmpty() && !Structure.getText().toString().isEmpty() && Email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+"))
            Signin(Name.getText().toString(), Surname.getText().toString(), Email.getText().toString(), Structure.getText().toString());
    }

    private void Signin(String name, String surname, String email, String structure){// méthode d'enregistrement

        StringRequest request = new StringRequest(Request.Method.POST, URL_REQUEST,
                response -> {
                    try{
                        JSONObject jsonObject = new JSONObject(response);
                        String success =  jsonObject.getString("success");


                        if(!success.isEmpty()){
                            Toast.makeText(FormActivity.this, success, Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(FormActivity.this, "Inscription Echoué. Veuillez Réessayer.", Toast.LENGTH_LONG).show();
                    }
                },
                error -> Toast.makeText(FormActivity.this, error.toString(), Toast.LENGTH_LONG).show()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError { // prendre les données
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("surname", surname);
                params.put("email", email);
                params.put("structure", structure);
                params.put("from_annonce", String.valueOf(id));
                return params;
            }

        };

        RequestQueue rQ = Volley.newRequestQueue(getApplicationContext());
        rQ.add(request);
    }
}