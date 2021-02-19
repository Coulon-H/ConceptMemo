package com.example.sign;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private Connection connection;

    // private final String host = "ssprojectinstance.csv2nbvvgbcb.us-east-2.rds.amazonaws.com"  // For Amazon Postgresql
    private final String host = "192.168.1.95";  // For Google Cloud Postgresql
    private final String database = "utilisateurs";
    private final int port = 5432;
    private final String user = "bomyron";
    private final String pass = "passforward";
    private String url = "jdbc:postgresql://%s:%d/%s";
    private boolean status;

    public Database() throws SQLException {
        this.url = String.format(this.url, this.host, this.port, this.database);
        //connect();
        //connection = DriverManager.getConnection(url, user, pass);
        //this.disconnect();
        //System.out.println("connection status:" + status);
    }

    private void connect() {
        Thread thread = new Thread(() -> {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, user, pass);
                status = true;
                System.out.println("connected:" + status);
            } catch (Exception e) {
                status = false;
                System.out.print(e.getMessage());
                e.printStackTrace();
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
            this.status = false;
        }
    }

    public Connection getConnection(){
        return this.connection;
    }

    public Connection getExtraConnection() throws SQLException {
        Connection c;
        c = DriverManager.getConnection(url, user, pass);

        return c;
    }
}
