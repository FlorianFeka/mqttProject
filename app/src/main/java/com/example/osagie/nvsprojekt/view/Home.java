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

/**
 * Created by Jonathan on 29.01.2018.
 */

public class Home extends AppCompatActivity {
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

        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Spinger");

        String[] poke = {"Ekans", "Pikachu", "Paras", "Ditto"};
        ListView listView = (ListView)findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(listView.getContext(), android.R.layout.simple_list_item_1, poke);
        listView.setAdapter(adapter);
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
                startActivity(intent);
                return true;
        }
        return true;
    }
}