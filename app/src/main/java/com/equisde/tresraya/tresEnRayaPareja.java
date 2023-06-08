package com.equisde.tresraya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class tresEnRayaPareja extends AppCompatActivity {
    public MediaPlayer bgm;
    private String elSiguiente = "o";
    public static final String GANADOR = "com.equisde.com.equisde.tresraya.GANADOR";

    private String ultimoJugador = "";
    private TextView[] tablero = new TextView[9];
    private int[][] jugadasGanadoras = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6},
            {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    private String valorCasilla = "x";
    private boolean estado = true;
    private TextView casillaTextView1, casillaTextView2, casillaTextView3, casillaTextView4,
            casillaTextView5, casillaTextView6, casillaTextView7, casillaTextView8,
            turnoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tres_en_raya);

        // Reproducir música de fondo
        bgm = MediaPlayer.create(getApplicationContext(), R.raw.bgm);
        bgm.start();
        bgm.setLooping(true);

        iniciarJuego();

        Intent intent = getIntent();
        turnoTextView = findViewById(R.id.turnoTextView);

        // Configurar los botones del menú y reiniciar
        Button menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(oyenteMenuButton);
        Button reiniciarButton = findViewById(R.id.reiniciarButton);
        reiniciarButton.setOnClickListener(oyenteReiniciarButton);
        reiniciarJuego();
    }

    public void iniciarJuego() {
        // Asignar los oyentes a cada casilla del tablero
        tablero[0] = findViewById(R.id.casillaTextView0);
        tablero[0].setOnClickListener(oyenteCasilla(0));

        tablero[1] = findViewById(R.id.casillaTextView1);
        tablero[1].setOnClickListener(oyenteCasilla(1));

        tablero[2] = findViewById(R.id.casillaTextView2);
        tablero[2].setOnClickListener(oyenteCasilla(2));

        tablero[3] = findViewById(R.id.casillaTextView3);
        tablero[3].setOnClickListener(oyenteCasilla(3));

        tablero[4] = findViewById(R.id.casillaTextView4);
        tablero[4].setOnClickListener(oyenteCasilla(4));

        tablero[5] = findViewById(R.id.casillaTextView5);
        tablero[5].setOnClickListener(oyenteCasilla(5));

        tablero[6] = findViewById(R.id.casillaTextView6);
        tablero[6].setOnClickListener(oyenteCasilla(6));

        tablero[7] = findViewById(R.id.casillaTextView7);
        tablero[7].setOnClickListener(oyenteCasilla(7));

        tablero[8] = findViewById(R.id.casillaTextView8);
        tablero[8].setOnClickListener(oyenteCasilla(8));
    }

    private View.OnClickListener oyenteCasilla(final int casilla) {
        // Responde al hacer clic en una casilla específica
        return view -> tocaCasilla(casilla);
    }

    private void reiniciarJuego() {
        // Reinicia el juego, limpiando las casillas y alternando el turno del jugador
        turnoTextView.setText("x");
        for (TextView textView : tablero)
            textView.setText("");

        valorCasilla = elSiguiente;
        elSiguiente = (elSiguiente.equals("o")) ? "x" : "o";
    }

    private View.OnClickListener oyenteMenuButton = v -> {
        // Botón de menú que regresa a la actividad principal
        Intent i = new Intent(tresEnRayaPareja.this, MainActivity.class);
        startActivity(i);
        finish(); // Terminar actividad actual
    };

    private View.OnClickListener oyenteReiniciarButton = v -> {
        // Reinicia el juego
        iniciarJuego();
        reiniciarJuego();
    };

    private void tocaCasilla(int casilla) {
        // Maneja el evento de tocar una casilla del tablero
        if (tablero[casilla].getText().equals("") && estado) {
            tablero[casilla].setText(valorCasilla);
            cambiarJugador();
            esJuegoTerminado();
        }
    }

    private void cambiarJugador() {
        // Alterna el turno del jugador actual
        valorCasilla = (valorCasilla.equals("x")) ? "o" : "x";
        turnoTextView.setText(valorCasilla);
    }

    private void esJuegoTerminado() {
        // Verifica si el juego ha terminado y si hay un ganador
        for (int[] jugada : jugadasGanadoras) {
            if (tablero[jugada[0]].getText().equals("x") &&
                    tablero[jugada[1]].getText().equals("x") &&
                    tablero[jugada[2]].getText().equals("x")) {
                // Jugador X gana
                ultimoJugador = "x";
                marcarGanador(jugada);
                estado = false;
                termina(1);
            } else if (tablero[jugada[0]].getText().equals("o") &&
                    tablero[jugada[1]].getText().equals("o") &&
                    tablero[jugada[2]].getText().equals("o")) {
                // Jugador O gana
                ultimoJugador = "o";
                marcarGanador(jugada);
                estado = false;
                termina(0);
            }
        }
    }

    private void marcarGanador(int[] jugada) {
        // Marca las casillas que forman una línea ganadora
        for (int casilla : jugada)
            tablero[casilla].setBackgroundColor(ContextCompat.getColor(this, R.color.verde_ganador));
    }

    private void termina(int valor) {
        // Termina el juego y muestra el resultado
        Intent i = new Intent(tresEnRayaPareja.this, Resultado.class);
        i.putExtra(GANADOR, valor);
        startActivity(i);
        finish();
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
