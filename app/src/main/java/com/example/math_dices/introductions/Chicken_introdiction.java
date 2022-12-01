package com.example.math_dices.introductions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.math_dices.R;
import com.example.math_dices.controller.SoundControll;
import com.example.math_dices.game_activity.Chicken_game_main;

public class Chicken_introdiction extends AppCompatActivity {
    Button nextBut;
    int ID;
    ImageButton onoffBut;
    SoundControll soundControll = new SoundControll();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicken_game_introduction);
        getSupportActionBar().hide();
        onoffBut = findViewById(R.id.SonoffBut2);
        nextBut = findViewById(R.id.nextBut2);
        soundControll.OnOffFun(Chicken_introdiction.this,onoffBut);
        Intent intent1 = getIntent();
        ID = intent1.getIntExtra("uID",0); // get ID from previous

        // next game 1 main activity function
        nextBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundControll.PopSoundFun2(view.getContext(),nextBut);
                Intent intent = new Intent();
                intent.setClass(view.getContext() , Chicken_game_main.class);
                intent.putExtra("uID",ID);
                startActivity(intent);
                soundControll.player.pause();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundControll.player.stop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        soundControll.player.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        soundControll.player.stop();
    }
}