package com.example.math_dices.controller;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.math_dices.R;

public class SoundControll extends AppCompatActivity {
    public MediaPlayer background;
    public MediaPlayer player;
    public MediaPlayer click;
    public MediaPlayer track;
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
    // pop sound
    public void PopSoundFun(Context context, ImageButton onoffBut)
    {
        track = MediaPlayer.create(context,R.raw.pop);
        track.start();
        track.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
    }
    public void PopSoundFun2(Activity main, Button onoffBut)
    {
        track = MediaPlayer.create(main,R.raw.pop);
        track.start();
        track.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
    }
    // roll the dice sound
    public void RollSoundFun(Context context)
    {
        track = MediaPlayer.create(context,R.raw.dicerollsound);
        for (int i = 0 ; i < 50;i++)
        {
            track.start();
        }
        track.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
    }
    // wrong sound
    protected void wrongSoundFun(Activity main)
    {

        track = MediaPlayer.create(main,R.raw.wrong);
        track.start();
        track.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
    }
    // correct sound
    protected void correctSoundFun(Activity main)
    {
        track = MediaPlayer.create(main,R.raw.correct);
        track.start();
        track.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
    }
    // winning sound
    public void winSoundFun(Activity main)
    {

        track= MediaPlayer.create(main,R.raw.winning);
        track.start();
        track.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
    }
    // running sound
    public void runSoundFun(Activity main)
    {
        track= MediaPlayer.create(main,R.raw.running);
        track.start();
        track.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
    }
    protected void runSoundStopFun(Activity main)
    {

        track= MediaPlayer.create(main,R.raw.running);
        track.stop();
        track.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
    }
    // go up sound
    public void upSoundFun(Activity main)
    {
        track= MediaPlayer.create(main,R.raw.upstair);
        track.start();
        track.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
    }
    protected void upSoundStopFun(Activity main)
    {
        track= MediaPlayer.create(main,R.raw.upstair);
        track.stop();
        track.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
    }

    // falling sound
    public void fallSoundFun(Activity main)
    {
        track= MediaPlayer.create(main,R.raw.fall);
        track.start();
        track.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
    }
    protected void fallSoundStopFun(Activity main)
    {
        track= MediaPlayer.create(main,R.raw.fall);
        track.stop();
        track.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
    }
    // hooray sound
    public void hooraySoundFun(Activity main)
    {
        track= MediaPlayer.create(main,R.raw.hooray);
        track.start();
        track   .setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
    }
}
