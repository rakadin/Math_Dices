package com.example.math_dices.winning_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.math_dices.HomePageActivity;
import com.example.math_dices.R;
import com.example.math_dices.controller.SoundControll;
import com.example.math_dices.game_activity.Egg_Game_main;
import com.example.math_dices.game_activity.Fishing_game_main;

public class Winning_activity_Fish extends AppCompatActivity {
    Button replayBut;
    Button menuBut;
    private int UID;
    SoundControll soundControl = new SoundControll();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_win);
        getSupportActionBar().hide();
        soundControl.winSoundFun(this);
        replayBut = findViewById(R.id.replayBut);
        menuBut = findViewById(R.id.menuBut);
        Intent intent1 = getIntent();
        UID = intent1.getIntExtra("uID",0);// lay id tu hoat dong truoc
        replayBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                soundControl.PopSoundFun(Winningactivity.this,);
                soundControl.PopSoundFun2(view.getContext(),replayBut);
                Intent intent = new Intent();
                intent.setClass(view.getContext(), Fishing_game_main.class);
                intent.putExtra("uID",UID);
                startActivity(intent);
//                soundControl.player.pause();
            }
        });
        menuBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundControl.PopSoundFun2(view.getContext(),replayBut);
                Intent intent = new Intent();
                intent.setClass(view.getContext(), HomePageActivity.class);
                intent.putExtra("uID",UID);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        soundControl.player.stop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundControl.player.stop();
    }
}