package com.example.math_dices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.math_dices.controller.SoundControll;

public class RegisterActivity extends AppCompatActivity {
    TextView backlogin;
    Button registerbut;
    ImageButton onoffbut;
    SoundControll soundControll = new SoundControll();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        // get id
        backlogin = findViewById(R.id.loginbut);
        onoffbut = findViewById(R.id.soundbut);
        registerbut = findViewById(R.id.loginbackbut);
        // chạy background music
        soundControll.OnOffFun(RegisterActivity.this,onoffbut);
        // kiem soat dang ky tai khoan nguoi dung
        registerbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundControll.clickSoundFun(RegisterActivity.this);
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        // đã có tài khoản và quay lại đăng nhập
        backlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundControll.clickSoundFun(RegisterActivity.this);
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        soundControll.player.release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundControll.player.stop();
    }
}