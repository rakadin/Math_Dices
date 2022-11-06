package com.example.math_dices.introductions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.math_dices.R;
import com.example.math_dices.controller.SoundControll;

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
    }
}