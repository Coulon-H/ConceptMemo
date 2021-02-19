package com.example.sign;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    TextView nameGetter, passwordGetter;
    Button login, redirect;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            database = new Database();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        nameGetter = findViewById(R.id.nameGetter);
        passwordGetter = findViewById(R.id.passwordGetter);

        login = findViewById(R.id.login);
        redirect = findViewById(R.id.redirect);

        login.setOnClickListener(v -> {
            try {
                Login();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        redirect.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(i);
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
        int idUser = sessionManagement.getSession();

        if(idUser != -1){
            moveToHome();
        }
    }

    private void Login() throws SQLException {
        User user = new User(nameGetter.getText().toString(), passwordGetter.getText().toString());

        if(verify(user) == 1) {
            SessionManagement sessionManagement = new SessionManagement(MainActivity.this);
            sessionManagement.saveSession(user);


            moveToHome();
        }else{
            Toast.makeText(getApplicationContext(), "Utilisateur non identifié", Toast.LENGTH_LONG).show();
        }
    }

    private void moveToHome(){
        Intent i = new Intent(MainActivity.this, HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    private int verify(User user) throws SQLException {
        String sql = "SELECT * FROM users WHERE name = ?";
        PreparedStatement preparedStatement = database.getExtraConnection().prepareStatement(sql);
        preparedStatement.setString(1, user.getName());

        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next())
            if(resultSet.getString("password").equals(user.getPassword())){
                user.setId(resultSet.getInt("id"));
                return 1;
            }

        return 0;
    }
}

