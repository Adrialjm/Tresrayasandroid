package com.equisde.tresraya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public MediaPlayer bgm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bgm = MediaPlayer.create(getApplicationContext(), R.raw.bgm);
        bgm.start();
        bgm.isLooping();

        findViewById(R.id.imageButton5).setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), tresEnRayaPareja.class);
            startActivity(intent);
        });
        findViewById(R.id.imageButton6).setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), TresEnRayaCPU.class);
            startActivity(intent);
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
