package com.example.math_dices.introductions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.math_dices.R;
import com.example.math_dices.controller.SoundControll;
import com.example.math_dices.game_activity.Fishing_game_main;
import com.example.math_dices.game_activity.Slide_game_main;

public class Fish_game_introduction extends AppCompatActivity {
    ImageButton onoffBut;
    SoundControll soundControl = new SoundControll();
    Button gamefBut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fishing_introduction);
        getSupportActionBar().hide();
        onoffBut = findViewById(R.id.SonoffBut2);
        gamefBut = findViewById(R.id.nextBut2);
        soundControl.OnOffFun(this,onoffBut);
        Intent intent = getIntent();
        int UID = intent.getIntExtra("uID",0);
        // next game 1 main activity function
        gamefBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundControl.PopSoundFun2(Fish_game_introduction.this,gamefBut);
                Intent intent2 = new Intent();
                intent2.setClass(Fish_game_introduction.this , Fishing_game_main.class);
                intent2.putExtra("uID",UID);
                startActivity(intent2);
                soundControl.player.pause();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundControl.player.stop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        soundControl.player.start();
    }
}