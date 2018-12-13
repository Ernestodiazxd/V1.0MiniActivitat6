package com.example.tmorales.fils;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.tmorales.fils.MainActivity.path_imatge;


// AsyncTask que descarrega la imatge de la xarxa, rep strings com a paràmetre d'entra
public class TascaDescarrega extends AsyncTask <String, Integer, Void> {
    // OnPreExecute s'executa abans que doInBackground
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Eliminem la imatge i posem la barra de progrés a 0
        MainActivity.barra_progres.setProgress(0);
        MainActivity.image_vista.setImageDrawable(null);
    }

    // Tasca que realitza les operacions de xarxa, no es pot manipular l'UI des d'a
    @Override
    protected Void doInBackground(String... url) {
        try {
        // Agafem la URL que s'ha passat com argument
            URL imatge = new URL(url[0]);
            // Fem la connexió a la URL i mirem la mida de la imatge
            HttpURLConnection connection = (HttpURLConnection) imatge.openConnection();
            int totalImatge = connection.getContentLength();
            // Creem l'input i un buffer on anirem llegint la informació
            InputStream inputstream = (InputStream) imatge.getContent();
            byte[] bufferImatge = new byte[1024];
            // Creem la sortida, és a dir, un objecte on guardarem la informació
            OutputStream outputstream = new FileOutputStream(path_imatge);
            int descarregat = 0;
            int count;
            // Mentre hi hagi informació per llegir
            while ((count = inputstream.read(bufferImatge)) != -1) {
            // Acumulem tot el que s'ha llegit
                descarregat += count;
                // Calculem el percentatge respecte el total i l'enviem a publishPr
                publishProgress(((descarregat * 100) / totalImatge));
                // Guardem al disc el que hem descarregat
                outputstream.write(bufferImatge, 0, count);
            }
            // Tanquem els "stream"
            inputstream.close();
            outputstream.close();
        } catch (IOException exception) {
            Log.d("ERR", "Alguna cosa no ha anat bé!");
            return null;
        }
        // No passem cap informació al onPostExecute
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
    // Actualitzem la barra de progrés amb el valor que se'ns ha enviat des de
        barra_progres.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
    // Quan acaba la tasca, inserim la imatge a l'ImageView
        image_vista.setImageDrawable(Drawable.createFromPath(path_imatge));
    }
}
