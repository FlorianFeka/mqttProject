package com.example.osagie.nvsprojekt.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.osagie.nvsprojekt.R;

/**
 * Created by Jonathan on 01.02.2018.
 */

public class UserWahl extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_wahl);

        String[] poke = {"Ekans", "Pikachu", "Paras", "Ditto"};
        ListView listView = (ListView)findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(listView.getContext(), android.R.layout.simple_list_item_1, poke);
        listView.setAdapter(adapter);
    }
}
