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

import com.example.math_dices.controller.SoundControll;
import com.example.math_dices.controller.UserRegisterDialog;
import com.example.math_dices.firebase.Send_Data_User;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {
    UserRegisterDialog userRegisterDialog = new UserRegisterDialog();
    TextInputLayout til1,til2,til3;
    TextView backlogin;
    Button registerbut;
    ImageButton onoffbut;
    EditText edtusname;
    EditText edtpass;
    EditText edtrepass;
    SoundControll soundControll = new SoundControll();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        Send_Data_User send = new Send_Data_User();
        send.getNewIDData(this); // lấy dữ liệu theo thời gian thực từ firebase
        // get id
        backlogin = findViewById(R.id.loginbut);
        onoffbut = findViewById(R.id.soundbut);
        registerbut = findViewById(R.id.loginbackbut);
        edtusname = findViewById(R.id.edtname);
        edtpass = findViewById(R.id.edtpassenter);
        edtrepass = findViewById(R.id.edtrepass);
        til1 = findViewById(R.id.textInputLayout2);
        til2 = findViewById(R.id.textInputLayout3);
        til3 = findViewById(R.id.textInputLayout4);
        // chạy background music
        soundControll.OnOffFun(RegisterActivity.this,onoffbut);

        // kiem soat dang ky tai khoan nguoi dung
        registerbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send.getNewIDData(view.getContext()); // lấy dữ liệu theo thời gian thực từ firebase
                soundControll.clickSoundFun(RegisterActivity.this);
                userRegisterDialog.checkuser(edtusname,view.getContext(),edtpass,edtrepass,til1,til2,til3);

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