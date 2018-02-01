package com.example.osagie.nvsprojekt.control;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Florian on 30.01.2018.
 */

public class AsyncTask_DB_Connection extends AsyncTask<Void,Void,Void> {
    public static Connection con;
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MQTT_PROJEKTMANAGMENT","root","mysql");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
