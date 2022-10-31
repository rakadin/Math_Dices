package com.example.math_dices.game_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.math_dices.R;

public class Egg_Game_main extends AppCompatActivity {
    private int ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        ID = intent.getIntExtra("uID",0); // get ID from previous
    }
}