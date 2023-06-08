package com.equisde.tresraya;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Random;

public class TresEnRayaCPU extends AppCompatActivity {

    private TextView[] tablero;
    private TextView turnoTextView;
    private Button reiniciarButton, menuButton;
    private String valorCasilla = "x";
    private String elSiguiente = "o";
    private boolean estado = true;
    private final int[][] jugadasGanadoras = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // filas
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // columnas
            {0, 4, 8}, {2, 4, 6} // diagonales
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tres_en_raya);

        tablero = new TextView[9];

        turnoTextView = findViewById(R.id.turnoTextView);

        reiniciarButton = findViewById(R.id.reiniciarButton);
        reiniciarButton.setOnClickListener(oyenteReiniciarButton);

        menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(oyenteMenuButton);

        iniciarJuego();
        reiniciarJuego();
    }

    private void iniciarJuego() {
        TextView casillaTextView0, casillaTextView1, casillaTextView2,
                casillaTextView3, casillaTextView4, casillaTextView5,
                casillaTextView6, casillaTextView7, casillaTextView8;

        casillaTextView0 = findViewById(R.id.casillaTextView0);
        casillaTextView0.setOnClickListener(oyenteCasilla0);
        tablero[0] = casillaTextView0;

        casillaTextView1 = findViewById(R.id.casillaTextView1);
        casillaTextView1.setOnClickListener(oyenteCasilla1);
        tablero[1] = casillaTextView1;

        casillaTextView2 = findViewById(R.id.casillaTextView2);
        casillaTextView2.setOnClickListener(oyenteCasilla2);
        tablero[2] = casillaTextView2;

        casillaTextView3 = findViewById(R.id.casillaTextView3);
        casillaTextView3.setOnClickListener(oyenteCasilla3);
        tablero[3] = casillaTextView3;

        casillaTextView4 = findViewById(R.id.casillaTextView4);
        casillaTextView4.setOnClickListener(oyenteCasilla4);
        tablero[4] = casillaTextView4;

        casillaTextView5 = findViewById(R.id.casillaTextView5);
        casillaTextView5.setOnClickListener(oyenteCasilla5);
        tablero[5] = casillaTextView5;

        casillaTextView6 = findViewById(R.id.casillaTextView6);
        casillaTextView6.setOnClickListener(oyenteCasilla6);
        tablero[6] = casillaTextView6;

        casillaTextView7 = findViewById(R.id.casillaTextView7);
        casillaTextView7.setOnClickListener(oyenteCasilla7);
        tablero[7] = casillaTextView7;

        casillaTextView8 = findViewById(R.id.casillaTextView8);
        casillaTextView8.setOnClickListener(oyenteCasilla8);
        tablero[8] = casillaTextView8;

        cambiarJugador();
    }

    private void reiniciarJuego() {
        for (TextView textView : tablero) {
            textView.setText("");
            textView.setTextColor(ContextCompat.getColor(this, R.color.negro));
        }

        estado = true;
        valorCasilla = "x";
        elSiguiente = "o";
        turnoTextView.setText("Turno: X");
    }

    private void cambiarJugador() {
        String temp = valorCasilla;
        valorCasilla = elSiguiente;
        elSiguiente = temp;

        if (valorCasilla.equals("o")) {
            turnoCPU();
        } else {
            turnoTextView.setText("Turno: " + valorCasilla.toUpperCase());
        }
    }

    private void comprobarGanador() {
        for (int[] f : jugadasGanadoras) {
            if (!tablero[f[0]].getText().equals("") &&
                    tablero[f[0]].getText().equals(tablero[f[1]].getText()) &&
                    tablero[f[0]].getText().equals(tablero[f[2]].getText())) {
                if (tablero[f[0]].getText().equals("x")) {
                    termina(1, f);
                } else {
                    termina(2, f);
                }
            }
        }

        if (estado) {
            estado = false;
            for (TextView textView : tablero) {
                if (textView.getText().equals("")) {
                    estado = true;
                    break;
                }
            }
            if (!estado) {
                termina(0, new int[]{});
            }
        }
    }

    private void termina(int ganador, int[] casillasGanadoras) {
        estado = false;
        if (ganador == 1) {
            for (int casilla : casillasGanadoras) {
                tablero[casilla].setTextColor(ContextCompat.getColor(this, R.color.ganador));
            }
            turnoTextView.setText("Ha ganado X");
        } else if (ganador == 2) {
            for (int casilla : casillasGanadoras) {
                tablero[casilla].setTextColor(ContextCompat.getColor(this, R.color.ganador));
            }
            turnoTextView.setText("Ha ganado O");
        } else {
            turnoTextView.setText("Empate");
        }
        // Reiniciar el juego después de mostrar el mensaje de empate
        Handler handler = new Handler();
        handler.postDelayed(this::reiniciarJuego, 2000);
    }

    private void turnoCPU() {
        // Buscar casilla vacía aleatoria
        Random random = new Random();
        int casillaAleatoria;
        do {
            casillaAleatoria = random.nextInt(9);
        } while (!tablero[casillaAleatoria].getText().equals(""));

        tablero[casillaAleatoria].setText("o");
        comprobarGanador();

        cambiarJugador();
    }


    private final View.OnClickListener oyenteCasilla0 = v -> {
        if (tablero[0].getText().equals("") && estado) {
            tablero[0].setText(valorCasilla);
            cambiarJugador();
            comprobarGanador();
        }
    };

    private final View.OnClickListener oyenteCasilla1 = v -> {
        if (tablero[1].getText().equals("") && estado) {
            tablero[1].setText(valorCasilla);
            cambiarJugador();
            comprobarGanador();
        }
    };

    private final View.OnClickListener oyenteCasilla2 = v -> {
        if (tablero[2].getText().equals("") && estado) {
            tablero[2].setText(valorCasilla);
            cambiarJugador();
            comprobarGanador();
        }
    };

    private final View.OnClickListener oyenteCasilla3 = v -> {
        if (tablero[3].getText().equals("") && estado) {
            tablero[3].setText(valorCasilla);
            cambiarJugador();
            comprobarGanador();
        }
    };

    private final View.OnClickListener oyenteCasilla4 = v -> {
        if (tablero[4].getText().equals("") && estado) {
            tablero[4].setText(valorCasilla);
            cambiarJugador();
            comprobarGanador();
        }
    };

    private final View.OnClickListener oyenteCasilla5 = v -> {
        if (tablero[5].getText().equals("") && estado) {
            tablero[5].setText(valorCasilla);
            cambiarJugador();
            comprobarGanador();
        }
    };

    private final View.OnClickListener oyenteCasilla6 = v -> {
        if (tablero[6].getText().equals("") && estado) {
            tablero[6].setText(valorCasilla);
            cambiarJugador();
            comprobarGanador();
        }
    };

    private final View.OnClickListener oyenteCasilla7 = v -> {
        if (tablero[7].getText().equals("") && estado) {
            tablero[7].setText(valorCasilla);
            cambiarJugador();
            comprobarGanador();
        }
    };

    private final View.OnClickListener oyenteCasilla8 = v -> {
        if (tablero[8].getText().equals("") && estado) {
            tablero[8].setText(valorCasilla);
            cambiarJugador();
            comprobarGanador();
        }
    };

    private final View.OnClickListener oyenteReiniciarButton = v -> reiniciarJuego();

    private final View.OnClickListener oyenteMenuButton = v -> {
        Intent intent = new Intent(TresEnRayaCPU.this, MainActivity.class);
        startActivity(intent);
        finish();
    };
}

