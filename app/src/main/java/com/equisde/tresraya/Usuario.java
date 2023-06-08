package com.equisde.tresraya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Usuario extends AppCompatActivity {

    public MediaPlayer bgm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        bgm = MediaPlayer.create(getApplicationContext(), R.raw.bgm);
        bgm.start();

        findViewById(R.id.button).setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), CrearUsuario.class);
            startActivity(intent);
        });

        SharedPreferences preferencesNombre = PreferenceManager.getDefaultSharedPreferences(Usuario.this);
        String texto1 = preferencesNombre.getString("nombre", "");
        EditText editNombre = findViewById(R.id.Nombre);
        editNombre.setText(texto1);

        SharedPreferences preferencesMail = PreferenceManager.getDefaultSharedPreferences(Usuario.this);
        String texto2 = preferencesMail.getString("mail", "");
        EditText editMail = findViewById(R.id.Mail);
        editMail.setText(texto2);

        EditText Nombre = findViewById(R.id.Nombre);
        editNombre.setFocusable(false);
        editNombre.setCursorVisible(false);

        EditText Mail = findViewById(R.id.Mail);
        editMail.setFocusable(false);
        editMail.setCursorVisible(false);
    }


    @Override
    protected  void onPause() {
        if (bgm != null) {
            bgm.release();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (bgm != null) {
            bgm.release();
        }
        super.onDestroy();
    }
    }

