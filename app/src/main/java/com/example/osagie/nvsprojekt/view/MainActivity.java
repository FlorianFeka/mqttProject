package com.example.osagie.nvsprojekt.view;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.osagie.nvsprojekt.R;
import com.example.osagie.nvsprojekt.control.AsyncTask_DB_Connection;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Map<String,String> data = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public void signIn_onClick(View c)
    {
        String username = ((EditText)findViewById(R.id.signIn_username)).getText().toString();
        data.put("username", username);
        String password = ((EditText)findViewById(R.id.signIn_password)).getText().toString();
        data.put("password",password);
        new AsyncTask_DB_Connection(this).execute("signIn",username,password);
    }

    public void register_onClick(View view){
        String username = ((EditText)findViewById(R.id.register_username)).getText().toString();
        data.put("username",username);
        String email = ((EditText)findViewById(R.id.register_email)).getText().toString();
        data.put("email",email);
        String password = ((EditText)findViewById(R.id.register_password)).getText().toString();
        data.put("password",password);
        new AsyncTask_DB_Connection(this).execute("register",username,email,password);
    }

    public void goToHome(){
        Intent intent = new Intent(this,Home.class);
        intent.putExtra("username",data.get("username"));
        startActivity(intent);
        this.finish();
    }

    public void showError(String errorMessage){
        Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show();
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
