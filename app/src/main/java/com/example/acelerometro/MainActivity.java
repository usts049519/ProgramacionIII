package com.example.acelerometro;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // declaración de variables
    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    // whip = gato, es un archivo de audio.
    int whip = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        final TextView sonido = (TextView)findViewById(R.id.tvSonido);
        if(sensor == null) finish();
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float x = sensorEvent.values[0];
                if(x < -5 && whip == 0){
                    whip++;
                    sonido.setText("Sonido "+whip);
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                }else if(x > 5 && whip == 1) {whip++;
                    sonido.setText("Sonido "+whip);
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                }
                if(whip==2){
                    whip=0;
                    sound();
                    sonido.setText("Sonido "+whip);
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        }; // se agrega el punto y coma
        star();
    }
    private void sound(){
        MediaPlayer mediaPlayer =
                MediaPlayer.create(this,R.raw.gato);
        mediaPlayer.start();
    }
    private void star(){
        sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
    private void stop(){
        sensorManager.unregisterListener(sensorEventListener);
    }
    /* agregar el sonido de látigo: hay que crear una carpeta dentro
    de la carpeta "res"
    * a la que llamremos "raw" alli almacenaremos el sonido.
    * ¿Cómo se hace? Clic derecho en "app" New+Android resource
    directory*/
    @Override
    protected void onPause() {
        stop();
        super.onPause();
    }@Override
    protected void onResume() {
        star();
        super.onResume();
    }
}