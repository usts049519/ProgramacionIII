package com.example.semana5app;
import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button iniciar, parar;
    MediaPlayer mediaPlayer;@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Aquí enlazamos nuestras variables con los vistas en el activity
        iniciar = (Button)findViewById(R.id.btIniciar);
        parar = (Button)findViewById(R.id.btDetener);
        mediaPlayer = MediaPlayer.create(this,R.raw.heart);
        // Los botones necesitas escuchar cuando se haga clic sucorrespondiente enlace en la vista.
                iniciar.setOnClickListener(this);
        parar.setOnClickListener(this);
    }
    // Este método permite a travez de un switch seleccionar la acción decada uno de los botones.
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btIniciar:
                play();
                break;
            case R.id.btDetener:
                stop();
                break;
        }
    }
    // Iniciar la canción
    private void play(){
        mediaPlayer.start();
    }
    // Para la ejecución de forma total
    private void stop(){
        mediaPlayer.stop();
    }

}

