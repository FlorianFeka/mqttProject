package com.example.osagie.nvsprojekt.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.osagie.nvsprojekt.R;
import com.example.osagie.nvsprojekt.control.AsyncTask_DB_Connection;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jonathan on 29.01.2018.
 */

public class Plus extends AppCompatActivity
{
    private Calendar calendar;
    private TextView textView_project_date_start,textView_project_date_end;
    private EditText projectName,client,description;
    private Map<String,String> data = new HashMap<>();
    private int year, month, day;
    private boolean isProject_date_start=true;
    private ArrayList<String> member=new ArrayList<>();
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.plus);
        Intent intent=getIntent();
        username=intent.getStringExtra("username");
        textView_project_date_start = (TextView) findViewById(R.id.textView_project_date_start);
        textView_project_date_end = (TextView) findViewById(R.id.textView_project_date_end);
        projectName=(EditText)findViewById(R.id.editText_project_name);
        client=(EditText)findViewById(R.id.editText_project_client);
        description=(EditText)findViewById(R.id.editText_project_description);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(true,year, month+1, day);
        Toast.makeText(this,"You need build version N",Toast.LENGTH_SHORT);
    }

    @SuppressWarnings("deprecation")
    public void setProjectStartDate(View view) {
        isProject_date_start = true;
        showDialog(123);
    }
    @SuppressWarnings("deprecation")
    public void setProjectEndDate(View view){
        isProject_date_start = false;
        showDialog(123);
    }

    public void addUser(View view)
    {
        Intent intent = new Intent(this, UserWahl.class);
        intent.putStringArrayListExtra("member",member);
        startActivityForResult(intent,1);
    }
    public void create(View view){
        String name=projectName.getText().toString();
        String client=this.client.getText().toString();
        String des=description.getText().toString();
        String start=textView_project_date_start.getText().toString();
        String end=textView_project_date_end.getText().toString();
        String names="";
        for(String d:member){
            names+=d+" ";
        }
        new AsyncTask_DB_Connection(this).execute("createProject",name,client,des,start,end,names,username);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            member=data.getStringArrayListExtra("member");
            String s="";
            for(String q:member){
                s+=" "+q;
            }
            Toast.makeText(this,s+" added as a members",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 123) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year - arg2 = month - arg3 = day
                    showDate(isProject_date_start,arg1, arg2+1, arg3);
                }
            };

    private void showDate(Boolean isProject_date_start, int year, int month, int day) {
        if(isProject_date_start){
            if(data.containsKey("project_date_start")){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    data.replace("project_date_start",day+"."+month+"."+year);
                }else {
                    Toast.makeText(this,"You need build version 24.",Toast.LENGTH_SHORT);
                }
            }else {
                data.put("project_date_start",day+"."+month+"."+year);
            }
            textView_project_date_start.setText(day+"."+month+"."+year);
        }else {
            if(data.containsKey("project_date_end")){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    data.replace("project_date_end",day+"."+month+"."+year);
                }else {
                    Toast.makeText(this,"You need build version 24.",Toast.LENGTH_SHORT);
                }
            }else {
                data.put("project_date_end",day+"."+month+"."+year);
            }
            textView_project_date_end.setText(day+"."+month+"."+year);
        }
    }
}
