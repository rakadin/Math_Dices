package com.example.math_dices.controller;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.math_dices.R;

public class SoundControll extends AppCompatActivity {
    public MediaPlayer background;
    public MediaPlayer player;
    public MediaPlayer click;
    protected boolean vali = true;

    // pplay intro sound
    public void introSoundFun(Activity main) {
        background = MediaPlayer.create(main, R.raw.intro);
        background.start();
        background.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                mediaPlayer.release();
            }
        });
    }
    // pplay click sound
    public void clickSoundFun(Activity main) {
        click = MediaPlayer.create(main, R.raw.pop);
        click.start();
        click.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                mediaPlayer.release();
            }
        });
    }

    // background sound control
    public void OnOffFun(Activity main, ImageButton onoffBut)
    {
        if (vali == true){
            onoffBut.setImageResource(R.drawable.sound_on);
        }
        //play sound
        if(player == null)
        {
            player = MediaPlayer.create(main,R.raw.backgroundmusic);
        }
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
        // Loop sound when play to the end
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
                player.start();
            }
        });

        // background sound control button function
        onoffBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vali == true)// when sound is on -> click -> off
                {
                    vali = false;
                    player.pause();
                    onoffBut.setImageResource(R.drawable.sound_off);
                }
                else// when sound is off -> click -> on
                {
                    vali = true;
                    player.start();
                    onoffBut.setImageResource(R.drawable.sound_on);
                }

            }
        });

    }
}
