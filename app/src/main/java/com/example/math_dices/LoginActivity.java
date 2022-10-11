package com.example.math_dices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.math_dices.controller.SoundControll;

public class LoginActivity extends AppCompatActivity {
    TextView registerTxt;
    ImageButton soundbut;
    Button loginbut;
    SoundControll soundControll = new SoundControll();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // get IDs
        soundbut = findViewById(R.id.soundbut);
        loginbut = findViewById(R.id.loginbut);
        registerTxt = findViewById(R.id.registerbut);
        getSupportActionBar().hide();// an di action bar

        soundControll.OnOffFun(LoginActivity.this,soundbut);// chay nhac nen
        loginbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,HomePageActivity.class);
                soundControll.clickSoundFun(LoginActivity.this);// chay âm thanh cick
                soundControll.player.release();// thoat player
                // neu login thoa man thi dang nhap thanh cong
                startActivity(intent);
            }
        });
        // điều hướng sang đăng ký
        registerTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                soundControll.clickSoundFun(LoginActivity.this);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        soundControll.player = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        soundControll.player.release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundControll.player.release();
    }
}