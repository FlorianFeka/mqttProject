package com.example.osagie.nvsprojekt.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.osagie.nvsprojekt.R;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Map<String,String> data = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void signIn_onClick(View c)
    {
        data.put("username",((EditText)findViewById(R.id.signIn_username)).getText().toString());
        data.put("password",((EditText)findViewById(R.id.signIn_password)).getText().toString());
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        this.finish();
    }

    public void register_onClick(View view){
        data.put("username",((EditText)findViewById(R.id.register_username)).getText().toString());
        data.put("email",((EditText)findViewById(R.id.register_email)).getText().toString());
        data.put("password",((EditText)findViewById(R.id.register_password)).getText().toString());
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
        this.finish();
    }

    public void radioGroup_sex_onClick(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.register_sex_m:
                if (checked)
                    data.put("geschlecht","maennlich");
                break;
            case R.id.register_sex_w:
                if (checked)
                    data.put("geschlect","weiblich");
                break;
        }

    }

}
