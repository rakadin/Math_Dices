package com.example.math_dices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.math_dices.controller.SoundControll;

public class HomePageActivity extends AppCompatActivity {
    ImageButton soundbut;
    SoundControll soundControll = new SoundControll();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().hide();
        soundbut = findViewById(R.id.SonoffBut);
        soundControll.OnOffFun(HomePageActivity.this,soundbut);// chay nhac nen
    }
    @Override
    protected void onStop() {
        super.onStop();
        soundControll.player.release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundControll.player.release();
    }
}