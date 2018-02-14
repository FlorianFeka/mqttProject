package com.example.osagie.nvsprojekt.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.osagie.nvsprojekt.R;
import com.example.osagie.nvsprojekt.model.domain.Project;
import com.example.osagie.nvsprojekt.model.domain.User;
import com.example.osagie.nvsprojekt.model.domain.User_in_project;

import java.util.ArrayList;

/**
 * Created by Florian on 14.02.2018.
 */

public class StatusListe extends AppCompatActivity {
    private int projectId, userId;
    private ArrayList<User> users;
    private ArrayList<String> usernames;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status_liste);
        usernames = new ArrayList<>();
    }

    public void setList(ArrayList<User> userlist){
        if(userlist!=null) {
            this.users = userlist;
            for(User u: userlist){
                usernames.add(u.getUsername());
            }
        }
        ListView listView = (ListView)findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(listView.getContext(), android.R.layout.simple_list_item_1, this.usernames);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {

            }
        });
    }




    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(ArrayList<String> usernames) {
        this.usernames = usernames;
    }
}
