package com.example.osagie.nvsprojekt.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.osagie.nvsprojekt.R;

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
    private Map<String,String> data = new HashMap<>();
    private int year, month, day;
    private boolean isProject_date_start=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.plus);

        textView_project_date_start = (TextView) findViewById(R.id.textView_project_date_start);
        textView_project_date_end = (TextView) findViewById(R.id.textView_project_date_end);
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
