package com.example.math_dices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.math_dices.controller.SoundControll;
import com.example.math_dices.controller.Utils;

public class MainActivity extends AppCompatActivity {


    TextView txt1;
    SoundControll controll = new SoundControll();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1 = findViewById(R.id.txtblink);
        getSupportActionBar().hide();// an di action bar

        // play intro sound
       controll.introSoundFun(MainActivity.this);

        // animation
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.blink_animation);
        txt1.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                txt1.setText("Math Dices Game");
                Utils.delay( 8 , ()->{
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    controll.background.stop();
                      }
                );
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}