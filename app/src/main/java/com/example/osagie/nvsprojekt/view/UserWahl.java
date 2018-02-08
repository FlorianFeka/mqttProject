package com.example.osagie.nvsprojekt.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.osagie.nvsprojekt.R;
import com.example.osagie.nvsprojekt.control.AsyncTask_DB_Connection;
import com.example.osagie.nvsprojekt.model.domain.User;
import com.example.osagie.nvsprojekt.model.repository.UserRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jonathan on 01.02.2018.
 */

public class UserWahl extends AppCompatActivity
{
    final ArrayList<String> list = new ArrayList<>();
    private List<String> member=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_wahl);
        Intent i=getIntent();
        member=i.getStringArrayListExtra("member");
        final ListView listView = (ListView)findViewById(R.id.list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(listView.getContext(), android.R.layout.simple_list_item_1, list);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String s =(listView.getItemAtPosition(i).toString());
                if(!member.contains(s)){
                    member.add(s);
                    Toast.makeText(UserWahl.this,s+" added as a member",Toast.LENGTH_SHORT).show();
                }
                else{
                    member.remove(s);
                    Toast.makeText(UserWahl.this,s+" removed from project",Toast.LENGTH_SHORT).show();
                }
            }
        });
        new AsyncTask_DB_Connection(this).execute("getAllUser");
    }

    public ArrayList<String> getList() {
        return list;
    }
    public void add(View c){
        Intent i=new Intent();
        i.putStringArrayListExtra("member",(ArrayList<String>) member);
        setResult(Activity.RESULT_OK, i);
        finish();
        super.onBackPressed();
    }
    public void setList(ArrayList<String> l) {
        for(String s:l){
            list.add(s);
        }
        ListView listView = (ListView)findViewById(R.id.list);
        ((ArrayAdapter)listView.getAdapter()).notifyDataSetChanged();
    }
}
