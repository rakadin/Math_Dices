package com.example.math_dices.winning_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.math_dices.R;

public class Winning_activity_chicken extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_win);
        getSupportActionBar().hide();
    }
}