package com.example.osagie.nvsprojekt.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

    /*  ArrayList<String> str = new ArrayList<>();
        //str.add(0,"Bisasam");

        try {
            fi = this.openFileInput("Test.txt");
            br = new BufferedReader(new InputStreamReader(fi));
            String sr;
            int i = 0;
            while ((sr = br.readLine()) != null) {
               str.add(i,sr);
                i++;
            }
        } catch (Exception e) {
        } finally {
            try {
                if (fi != null)
                    fi.close();
                if (br != null)
                    br.close();
            } catch (IOException io) {
                Log.i("xxx", io.getMessage());
            }
        }*/
        Intent i=getIntent();
        username=i.getStringExtra("username");
        final ListView listView = (ListView)findViewById(R.id.list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(listView.getContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
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
                startActivity(intent);
                return true;
        }
        return true;
    }


    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<Project> list) {
        for(Project p:list){
            this.list.add(p.getProjectname());
        }
        ListView listView = (ListView)findViewById(R.id.list);
        ((ArrayAdapter)listView.getAdapter()).notifyDataSetChanged();

    }
}