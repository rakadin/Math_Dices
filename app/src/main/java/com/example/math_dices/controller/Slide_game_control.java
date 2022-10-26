package com.example.math_dices.controller;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Slide_game_control {
    int check =0;
    SoundControll soundControl = new SoundControll();
    public void checkAns(TextView top, TextView bot, int move, int right[], int left[], Activity main, Context context)
    {
        if(move >0)
        {
            top.setText(""+left[move-1]);
            bot.setText(""+right[move-1]);
            top.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(left[move-1] > right[move-1])
                    {
                        soundControl.correctSoundFun(main);
                        Toast.makeText(context,"Đúng rồi!",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        soundControl.wrongSoundFun(main);
                        Toast.makeText(context,"Sai rồi!",Toast.LENGTH_LONG).show();
                    }
                }

            });
            bot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(left[move-1] < right[move-1])
                    {
                        soundControl.correctSoundFun(main);
                        Toast.makeText(context,"Đúng rồi!",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        soundControl.wrongSoundFun(main);
                        Toast.makeText(context,"Sai rồi!",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        else
        {

        }
    }
}
