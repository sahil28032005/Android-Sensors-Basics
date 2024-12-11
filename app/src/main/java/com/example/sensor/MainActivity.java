package com.example.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SensorManager sm=(SensorManager) getSystemService(SENSOR_SERVICE);
        if(sm!=null){
            Sensor proxi=sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            if(proxi!=null){
                sm.registerListener(this,proxi,SensorManager.SENSOR_DELAY_NORMAL);
            }
        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType()==Sensor.TYPE_PROXIMITY){
            ((TextView)findViewById(R.id.tv1)).setText("values"+sensorEvent.values[0]);
            if(sensorEvent.values[0]>0){
                Toast.makeText(this, "contact destroyed", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "contact established", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}