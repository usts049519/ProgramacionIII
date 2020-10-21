package com.example.camara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public class MainActivity extends AppCompatActivity {
        // objetos que se enlaza con las vistas
        ImageView imageView;
        Button btfoto;
        // para administrar la toma de la foto cuando se ejecuta la Intent
        static final int REQUEST_IMAGE_CAPTURE = 1;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            // Enlaces con las vistas
            btfoto = (Button)findViewById(R.id.btTomarFoto);
            imageView = (ImageView)findViewById(R.id.ivMostrarFoto);
            // Evento clic del botón
            btfoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    llamarIntent();
                }
            });
        }
        private void llamarIntent(){
            Intent takePictureIntent = new
                    Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) !=null) {
                startActivityForResult(takePictureIntent,
                        REQUEST_IMAGE_CAPTURE);
            }
        }
        // Si la captura de la imagen se realzó se para al imageView la foto
        @Override
        protected void onActivityResult(int requestCode, int resultCode,
                                        Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode ==
                    RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(imageBitmap);
            }
        }
    }
}