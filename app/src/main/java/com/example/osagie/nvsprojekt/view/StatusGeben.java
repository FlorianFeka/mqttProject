package com.example.osagie.nvsprojekt.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.osagie.nvsprojekt.R;
import com.example.osagie.nvsprojekt.control.MqttPMClient;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by Jonathan on 10.02.2018.
 */

public class StatusGeben  extends AppCompatActivity {
    private int projectId;
    private int userId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status_geben);
        Intent i = getIntent();
        projectId = i.getIntExtra("projectId",-1);
        userId = i.getIntExtra("userId",-1);
    }

    public void onClickSend(View view){
        if(projectId < 0 || userId < 0){
            alarm("Fehler beim Versuch das Projekt zu finden!");
            return;
        }
        String status = ((EditText)findViewById(R.id.editText_status)).getText().toString();
        MqttPMClient mqttPMClient = new MqttPMClient(userId);
        mqttPMClient.publish(status,projectId,userId);
    }
    public void alarm(String text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }
}