package com.example.math_dices.game_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.math_dices.R;

public class Chicken_game_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicken_game_main);
        getSupportActionBar().hide();
    }
}