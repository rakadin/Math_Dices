package com.example.math_dices;

import android.content.Intent;
//import android.support.design.widget.TextInputLayout;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.math_dices.controller.LoginDialog;
import com.example.math_dices.controller.SoundControll;
import com.example.math_dices.firebase.Send_Data_User;
import com.example.math_dices.sqlite.ArchivementDAO;
import com.example.math_dices.sqlite.UserDAO;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    TextView registerTxt;
    ImageButton soundbut;
    Button loginbut;
    EditText uname,upass;
    TextInputLayout til1,til2;
    SoundControll soundControll = new SoundControll();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UserDAO userDAO = new UserDAO(this);
        ArchivementDAO ar = new ArchivementDAO(this);
//        userDAO.deleteTable();
//        ar.deleteTable();
        Send_Data_User send = new Send_Data_User();
        send.getNewIDData(this); // lấy dữ liệu theo thời gian thực
        /*
        them database
         */
        // get IDs
        soundbut = findViewById(R.id.soundbut);
        loginbut = findViewById(R.id.loginbut);
        registerTxt = findViewById(R.id.registerbut);
        uname = findViewById(R.id.edtusername);
        upass = findViewById(R.id.edtpass);
        til1 = findViewById(R.id.textInputLayout5);
        til2 = findViewById(R.id.textInputLayout);
        getSupportActionBar().hide();// an di action bar

        soundControll.OnOffFun(LoginActivity.this,soundbut);// chay nhac nen
        loginbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundControll.clickSoundFun(LoginActivity.this);// chay âm thanh cick
                LoginDialog loginDialog = new LoginDialog();
                loginDialog.validate(uname,view.getContext(),upass,til1,til2);
                soundControll.player.release();// thoat player

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