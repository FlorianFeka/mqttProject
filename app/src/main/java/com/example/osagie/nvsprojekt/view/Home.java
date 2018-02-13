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

import java.util.ArrayList;

/**
 * Created by Jonathan on 29.01.2018.
 */

public class Home extends AppCompatActivity {
    private ArrayList<String> list = new ArrayList<>();
    private String username;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        Intent i=getIntent();
        username=i.getStringExtra("username");
        this.setList(null);
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
                startActivityForResult(intent,1);
                return true;
        }
        return true; //wait what true and true? like wat?!!?
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Intent i=getIntent();
            username=i.getStringExtra("username");
            new AsyncTask_DB_Connection(this).execute("getAllProjectWithUser",username);
        }
    }
    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<Project> list) {
        if(list!=null) {
            this.list=new ArrayList<>();
            for (Project p : list) {
                this.list.add(p.getProjectname());
            }
        }
        ListView listView = (ListView)findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(listView.getContext(), android.R.layout.simple_list_item_1, this.list);
        listView.setAdapter(adapter);
        //((ArrayAdapter)listView.getAdapter()).notifyDataSetChanged();

    }
}