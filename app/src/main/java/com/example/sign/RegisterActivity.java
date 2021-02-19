package com.example.sign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    TextView nameSetter, passwordSetter;
    Button register;
    User u;
    Database d;

    private final String URL_REQUEST = "http://192.168.1.68/R_L/register0.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            d = new Database();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        nameSetter = findViewById(R.id.nameSetter);
        passwordSetter = findViewById(R.id.passwordSetter);

        register = findViewById(R.id.register);

        register.setOnClickListener(v -> {
            try {
                registerPostGres();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }


    private void register(){// méthode d'enregistrement
        u = new User(nameSetter.getText().toString(), passwordSetter.getText().toString());
        StringRequest request = new StringRequest(Request.Method.POST, URL_REQUEST,
                response -> {
                    try{
                        JSONObject jsonObject = new JSONObject(response);
                        String success =  jsonObject.getString("success");
                        System.out.println(jsonObject);
                        if(success.equals("1")){
                            Toast.makeText(RegisterActivity.this, "Registeration Successful", Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(RegisterActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                    }
                },
                error -> Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_LONG).show()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError { // prendre les données
                Map<String, String> params = new HashMap<>();
                params.put("name", u.getName());
                params.put("password", u.getPassword());
                return params;
            }

        };

        request.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });

        RequestQueue rQ = Volley.newRequestQueue(getApplicationContext());
        rQ.add(request);
    }

    private void registerPostGres() throws SQLException {
        u = new User(nameSetter.getText().toString(), passwordSetter.getText().toString());
        Connection c = d.getExtraConnection();
        ResultSet resultSet;
        String sql = "INSERT INTO users(name, password) VALUES (?, ?)";

        try{
            PreparedStatement preparedStatement = c.prepareStatement(sql);
            preparedStatement.setString(1, u.getName());
            preparedStatement.setString(2, u.getPassword());

            preparedStatement.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}