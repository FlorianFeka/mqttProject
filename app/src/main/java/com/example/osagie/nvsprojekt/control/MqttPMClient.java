package com.example.osagie.nvsprojekt.control;

import android.widget.EditText;

import com.example.osagie.nvsprojekt.R;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.MqttClient;

/**
 * Created by Florian on 14.02.2018.
 */

public class MqttPMClient implements MqttCallback{
    private final String broker       = "tcp://10.0.0.130:1883";
    private String clientId     = "MQTT_CLIENT_PM";

    public MqttPMClient(int userId){
        clientId+="-"+userId;
    }

    public void subscribe(int projectId, int userId){
        String topic        = "MQTT_PROJECTMANAGEMENT/"+projectId+"/"+userId;
        try {
            MemoryPersistence persistence = new MemoryPersistence();
            MqttClient client = new MqttClient(broker, clientId, persistence);
            client.connect();
            client.setCallback(this);
            client.subscribe(topic);
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }

    public void publish(String status,int projectId,int userId){
        String topic        = "MQTT_PROJECTMANAGEMENT/"+projectId+"/"+userId;
        int qos             = 2;
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            MqttClient client = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker);
            client.connect(connOpts);
            System.out.println("Connected");
            System.out.println("Publishing message: "+status);
            MqttMessage message = new MqttMessage(status.getBytes());
            message.setRetained(true);
            message.setQos(qos);
            client.publish(topic, message);
            System.out.println("Message published");
            client.disconnect();
            System.out.println("Disconnected");
            System.exit(0);
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
