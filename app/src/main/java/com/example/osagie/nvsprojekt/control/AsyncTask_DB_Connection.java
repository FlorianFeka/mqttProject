package com.example.osagie.nvsprojekt.control;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.osagie.nvsprojekt.model.domain.Project;
import com.example.osagie.nvsprojekt.model.domain.User_in_project;
import com.example.osagie.nvsprojekt.model.repository.ProjectRepository;
import com.example.osagie.nvsprojekt.model.repository.UserRepository;
import com.example.osagie.nvsprojekt.model.repository.User_in_projectRepository;
import com.example.osagie.nvsprojekt.view.Home;
import com.example.osagie.nvsprojekt.view.MainActivity;

import com.example.osagie.nvsprojekt.model.domain.User;
import com.example.osagie.nvsprojekt.view.Plus;
import com.example.osagie.nvsprojekt.view.UserWahl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Florian on 30.01.2018.
 */
@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
public class AsyncTask_DB_Connection extends AsyncTask<String,Void,Boolean> {
    private static Connection con;
    private String[] data;
    private User user;
    private MainActivity mainActivity;
    private UserWahl userWahl;
    private Home home;
    private Plus plus;
    private String errorMessage="";
    private ArrayList<String> usernames;
    private ArrayList<Project> projectWithUsername;

    public AsyncTask_DB_Connection(MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }
    public AsyncTask_DB_Connection(UserWahl userWahl){this.userWahl=userWahl;}
    public AsyncTask_DB_Connection(Home home){this.home=home;}
    public AsyncTask_DB_Connection(Plus plus){this.plus=plus;}
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            data = strings;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://10.0.2.2:3306/mqtt_projectmanagement","nvs","nvs");
            con.setAutoCommit(false);
            UserRepository userRepository = new UserRepository();
            ProjectRepository projectRepository=new ProjectRepository();
            User_in_projectRepository userInProjectRepository=new User_in_projectRepository();
            if(data[0].equals("signIn")){
                user = userRepository.findByUsernameAndPassword(con,data[1],data[2]);
                    if(user!=null){
                        return true;
                    }else {
                        setErrorMessage("User wurde nicht gefunden.");
                        return false;
                    }
            }else if(data[0].equals("register")){
                user = new User(data[1],data[2],data[3]);
                if(!userRepository.exist(con,user)){
                    int erg = userRepository.insert(con,user);
                    if(erg>0){
                        return true;
                    }else{
                        setErrorMessage("User konnte nicht hinzugefügt werden, versuchen sie es später noch einmal");
                    }
                }else{
                    setErrorMessage("User existiert schon.");
                }
            }
            if(data[0].equals("getAllUser")){
                List<User> list=new ArrayList<>();
                list=userRepository.findAll(con);
                 usernames = new ArrayList<>();
                for(User u:list){
                    usernames.add(u.getUsername());
                }
                return true;
            }
            if(data[0].equals("getAllProjectWithUser")){
                List<User_in_project> list=new ArrayList<>();
                list=userInProjectRepository.findAll(con);
                projectWithUsername=new ArrayList<>();
                String username=data[1];
                int i=userRepository.findByUsername(con,username);
                for(User_in_project uip:list){
                    if(uip.getUser()==i)
                    projectWithUsername.add(projectRepository.findById(con,i));
                }
                return true;
            }
            if(data[0].equals("createProject")){
                DateFormat format = new SimpleDateFormat(" d.M.yyyy");
                java.util.Date date= Calendar.getInstance().getTime();
                Project p =new Project(data[1],data[2],data[3],new Date((format.parse(data[4]).getTime())),new Date((format.parse(data[5]).getTime())),new Timestamp(date.getTime()));
                int id=projectRepository.insert(con,p);
                String[] names=data[6].split(" ");
                ArrayList<Integer> ar=new ArrayList<>();
                for(String name:names){
                    if(!name.equals(data[7]))
                        ar.add(userRepository.findByUsername(con,name));
                }
                User_in_project b=new User_in_project(userRepository.findByUsername(con,data[7]),id,1);
                userInProjectRepository.insert(con,b);
                for(Integer i:ar){
                    User_in_project uip=new User_in_project(i,id,2);
                    userInProjectRepository.insert(con,uip);
                }
                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if(aBoolean) {
            switch (data[0]) {
                case "getAllUser":
                    userWahl.setList(usernames);
                    break;
                case "getAllProjectWithUser":
                    home.setList(projectWithUsername);
                    break;
                default:
                    mainActivity.goToHome();
                    break;
            }
        }
        else{
            if(mainActivity!=null){
                mainActivity.showError(getErrorMessage());
            }
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
