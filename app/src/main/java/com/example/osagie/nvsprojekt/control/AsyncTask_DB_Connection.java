package com.example.osagie.nvsprojekt.control;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.osagie.nvsprojekt.model.repository.UserRepository;
import com.example.osagie.nvsprojekt.view.MainActivity;

import com.example.osagie.nvsprojekt.model.domain.User;

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
        this.mainActivity=mainActivity;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            data = strings;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://10.0.0.1:3306/mqtt_projectmanagement","root","toor");
            UserRepository userRepository = new UserRepository();
            if(data[0].equals("signIn")){
                user = userRepository.findByUsernameAndPassword(con,strings[1],strings[2]);
                    if(user!=null){
                        return true;
                    }else {
                        setErrorMessage("User wurde nicht gefunden.");
                        return false;
                    }
            }else if(data[0].equals("register")){
                int erg = userRepository.insert(con,new User(data[1],data[2],data[3]));
                if(erg == 0){
                    if(userRepository.exist(con,user)){
                        return true;
                    }else{
                        setErrorMessage("User existiert schon.");
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if(aBoolean){
            mainActivity.goToHome();
        }else{
            mainActivity.showError(getErrorMessage());
        }
    }

    public String getErrorMessage() {
        return errorMessage;
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
