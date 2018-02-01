package com.example.osagie.nvsprojekt.control;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import com.example.osagie.nvsprojekt.view.MainActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Florian on 30.01.2018.
 */


@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
public class AsyncTask_DB_Connection extends AsyncTask<String,Void,Boolean> {
    private static Connection con;
    private String[] data;
    private User user;
    private MainActivity mainActivity;
    private String errorMessage;

    public AsyncTask_DB_Connection(MainActivity mainActivity){
        setMainActivity(mainActivity);
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            setData(strings);
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MQTT_PROJEKTMANAGMENT","root","toor");
            UserRepository userRepository = new UserRepository();
            if(data[0].equals("signIn")){
                setUser(userRepository.findByUsernameAndPassword());a
                if(user!=null){
                    return true;
                }else {
                    setErrorMessage("User wurde nicht gefunden.");
                }
            }else if(data[0].equals("register")){
                setUser(userRepository.insert(new User(data[1],data[2],data[3])));
                if(user!=null){
                    if(userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword())==null){
                        return true;
                    }
                }
            }
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if(aBoolean){
            switch (data[0]){
                case "signIn":

                    break;
                case "register":
                    break;
            }

        }
    }


    public void setData(String[] data) {
        this.data = data;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
    private void setErrorMessage(String errorMessage){
        this.errorMessage=errorMessage;
    }
}
