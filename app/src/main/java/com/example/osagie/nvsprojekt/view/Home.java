package com.example.osagie.nvsprojekt.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.osagie.nvsprojekt.R;
import com.example.osagie.nvsprojekt.control.AsyncTask_DB_Connection;
import com.example.osagie.nvsprojekt.model.domain.Project;
import com.example.osagie.nvsprojekt.model.domain.User_in_project;
import com.example.osagie.nvsprojekt.model.repository.User_in_projectRepository;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 29.01.2018.
 */

public class Home extends AppCompatActivity {
    private List<String> list = new ArrayList<>();
    private String username;
    private int userId;
    private List<User_in_project> user_in_projects;
    private ArrayList<Project> projects;
    private Intent i;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        i=getIntent();
        username=i.getStringExtra("username");
        userId = i.getIntExtra("userId",-1);
        this.setList(null,null);
        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Spinger");
        new AsyncTask_DB_Connection(this).execute("getAllProjectWithUser",username);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.addId:
                Intent intent = new Intent(this, Plus.class);
                intent.putExtra("username",username);
                intent.putExtra("userId",userId);
                startActivityForResult(intent,1);
                return true;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Intent i=getIntent();
            username=i.getStringExtra("username");
            userId = i.getIntExtra("userId",-1);
            new AsyncTask_DB_Connection(this).execute("getAllProjectWithUser",username);
        }
    }
    public List<String> getList() {
        return list;
    }

    public void setList(ArrayList<Project> list, List<User_in_project> uips) {
        if(list!=null) {
            this.user_in_projects = uips;
            this.projects = list;
            this.list=new ArrayList<>();
            for (Project p : list) {
                this.list.add(p.getProjectname());
            }
        }
        ListView listView = (ListView)findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(listView.getContext(), android.R.layout.simple_list_item_1, this.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                if(getList()!=null){
                    int projectId =((Project) projects.get(position)).getId();
                    for(User_in_project user_in_project: user_in_projects){
                        if(user_in_project.equals(projectId)){
                            int project_memeber_type = user_in_project.getProject_member_type();
                            if(project_memeber_type==1){

                            }else if(project_memeber_type==2){

                            }
                        }
                    }
                }
            }
        });
        //((ArrayAdapter)listView.getAdapter()).notifyDataSetChanged();
    }

    public void statusSehen(int projectId){
        Intent intent = new Intent(this, StatusSehen.class);
        intent.putExtra("projectId",projectId);
        startActivity(intent);
    }

    public void statusGeben(int projectId){
        Intent intent = new Intent(this, StatusGeben.class);
        intent.putExtra("projectId",projectId);
        startActivity(intent);
    }

    public void alarm(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}