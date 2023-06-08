package com.equisde.tresraya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CrearUsuario extends AppCompatActivity {

    public MediaPlayer bgm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearusuario);

        bgm = MediaPlayer.create(getApplicationContext(), R.raw.bgm);
        bgm.start();

        findViewById(R.id.button2).setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), Usuario.class);
            startActivity(intent);
        });

        final EditText editNombre = findViewById(R.id.Nombre);
        editNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(CrearUsuario.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("nombre", s.toString());
                editor.apply();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });

        final EditText editMail = findViewById(R.id.Mail);
        editMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(CrearUsuario.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("mail", s.toString());
                editor.apply();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });

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

