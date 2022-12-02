package com.example.math_dices.introductions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.math_dices.R;
import com.example.math_dices.controller.SoundControll;
import com.example.math_dices.game_activity.Card_game_main;
import com.example.math_dices.game_activity.Egg_Game_main;

public class Card_introduction extends AppCompatActivity {
    Button nextBut;
    int ID;
    ImageButton onoffBut;
    SoundControll soundControll = new SoundControll();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_game_introduction);
        getSupportActionBar().hide();
        onoffBut = findViewById(R.id.SonoffBut2);
        soundControll.OnOffFun(this,onoffBut);
        nextBut = findViewById(R.id.nextBut2);
        Intent intent = getIntent();
        ID = intent.getIntExtra("uID",0); // get ID from previous
        // next game 1 main activity function
        nextBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundControll.PopSoundFun2(view.getContext(),nextBut);
                Intent intent2 = new Intent();
                intent2.setClass(view.getContext() ,Card_game_main.class);
                intent2.putExtra("uID",ID);
                startActivity(intent2);
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
}