package com.example.math_dices.introductions;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.math_dices.R;
import com.example.math_dices.controller.SoundControll;
import com.example.math_dices.game_activity.Slide_game_main;

/*
   slide game introduction activity
 */
public class Slide_introduction extends AppCompatActivity {
    ImageButton onoffBut;
    SoundControll soundControl = new SoundControll();
    Button game1But;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_introduction);
        getSupportActionBar().hide();
        onoffBut = findViewById(R.id.SonoffBut2);
        game1But = findViewById(R.id.nextBut2);
        soundControl.OnOffFun(Slide_introduction.this,onoffBut);
        Intent intent = getIntent();
        int UID = intent.getIntExtra("uID",0);
        // next game 1 main activity function
        game1But.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundControl.PopSoundFun2(Slide_introduction.this,game1But);
                Intent intent2 = new Intent();
                intent2.setClass(Slide_introduction.this , Slide_game_main.class);
                intent2.putExtra("uID",UID);
                startActivity(intent2);
                soundControl.player.pause();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundControl.player.release();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        soundControl.player.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        soundControl.player.stop();
        soundControl.player.release();
    }
}