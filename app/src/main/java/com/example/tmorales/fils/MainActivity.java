package com.example.tmorales.fils;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    // TODO Indiquem aquí la URL de la imatge que volem descarregar
    public static String img = "";
    // Crearem el recurs a la memòria del telèfon, definim la ruta fins el nom del fitx
    public static String path_imatge = Environment.getExternalStorageDirectory().toString();
    // Declarem els components de la UI
    public Button btn_descarrega;
    public ProgressBar barra_progres;
    public ImageView image_vista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Afegim el listener al botó
        btn_descarrega = (Button) findViewById(R.id.buttonDescarrega);
        btn_descarrega.setOnClickListener();
        // Posem la barra de progrés amb un màxim de 100
        barra_progres = (ProgressBar) findViewById(R.id.progressBar);
        barra_progres.setMax(100);
        // Assignem l'ImageView de la vista
        image_vista = (ImageView) findViewById(R.id.imageView);
    }

    @Override
    public void onClick(View v) {
        // Executem l'AsyncTask passant-li com a argument la ruta de la imatge.
        if (v == btn_descarrega) new TascaDescarrega().execute(img);
    }
}

