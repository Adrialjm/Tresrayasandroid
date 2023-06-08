package com.equisde.tresraya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Resultado extends AppCompatActivity {
    TextView mensaje;
    TextView jugadorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        mensaje = findViewById(R.id.mensajePuntoTextView);
        jugadorTextView = findViewById(R.id.jugadorTextView);

        Intent i = getIntent(); //getIntent() obtine el Intent que inicio la actividad actual
        int valor = i.getIntExtra(tresEnRayaPareja.GANADOR, 0);
        Button menuButton = findViewById(R.id.menuBtn);
        menuButton.setOnClickListener(oyenteMenuButoon);

        if (valor == 0)
            jugadorTextView.setText("o");
        else if (valor == 1)
            jugadorTextView.setText("x");
    }

    private final View.OnClickListener oyenteMenuButoon = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(Resultado.this, MainActivity.class);
            startActivity(i);
            finish();  //Terminar actividad actual
        }
    };
}