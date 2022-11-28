package com.example.math_dices.introductions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.math_dices.R;
import com.example.math_dices.controller.SoundControll;
import com.example.math_dices.game_activity.Egg_Game_main;
import com.example.math_dices.game_activity.Slide_game_main;

public class Egg_Game_Introduction extends AppCompatActivity {
    Button nextBut;
    int ID;
    ImageButton onoffBut;
    SoundControll soundControll = new SoundControll();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);
        getSupportActionBar().hide();
        onoffBut = findViewById(R.id.SonoffBut);
        soundControll.OnOffFun(this,onoffBut);
        nextBut = findViewById(R.id.nextBut);
        Intent intent = getIntent();
        ID = intent.getIntExtra("uID",0); // get ID from previous
        // next game 1 main activity function
        nextBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundControll.PopSoundFun2(view.getContext(),nextBut);
                Intent intent2 = new Intent();
                intent2.setClass(view.getContext() , Egg_Game_main.class);
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