package com.example.math_dices.controller;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.math_dices.R;
/*
game 1- eggs pick control the gameplay class
 */
public class GameplayGame1 extends AppCompatActivity {
    public int countEggs = 0;
    SoundControll soundControl = new SoundControll();

    public void gameOn(int diceNum, ImageView[] imgEgg, Button butClick, Activity main) {
        int temID = butClick.getId();
        switch (diceNum) {
            case 1: {
                if (temID == R.id.grEgg3 || temID == R.id.grEgg4) {
                    soundControl.correctSoundFun(main);
                    butClick.setBackgroundResource(R.drawable.xsign);
                    imgEgg[countEggs].setImageResource(R.drawable.green_egg);
                    countEggs++;
                } else {
                    soundControl.wrongSoundFun(main);
                }
                break;
            }
        case 2: {
            if (temID == R.id.grEgg2 || temID == R.id.grEgg7 || temID == R.id.yellowEgg2 || temID == R.id.redEgg1) {
                soundControl.correctSoundFun(main);
                butClick.setBackgroundResource(R.drawable.xsign);
                if (temID == R.id.yellowEgg2) {
                    imgEgg[countEggs].setImageResource(R.drawable.yellow_egg);
                }
                if (temID == R.id.redEgg1) {
                    imgEgg[countEggs].setImageResource(R.drawable.red_egg);
                }
                if (temID == R.id.grEgg2 || temID == R.id.grEgg7) {
                    imgEgg[countEggs].setImageResource(R.drawable.green_egg);
                }
                countEggs++;
            } else {
                soundControl.wrongSoundFun(main);
            }
            break;
        }
        case 3: {
            if (temID == R.id.redEgg2 || temID == R.id.redEgg3 || temID == R.id.purEgg2) {
                soundControl.correctSoundFun(main);
                butClick.setBackgroundResource(R.drawable.xsign);
                if (temID == R.id.purEgg2) {
                    imgEgg[countEggs].setImageResource(R.drawable.purple_egg);
                }
                if (temID == R.id.redEgg2 || temID == R.id.redEgg3) {
                    imgEgg[countEggs].setImageResource(R.drawable.red_egg);
                }
                countEggs++;
            } else {
                soundControl.wrongSoundFun(main);
            }
            break;
        }
        case 4: {
            if (temID == R.id.yellowEgg1 || temID == R.id.blueEgg2) {
                soundControl.correctSoundFun(main);
                butClick.setBackgroundResource(R.drawable.xsign);
                if (temID == R.id.yellowEgg1) {
                    imgEgg[countEggs].setImageResource(R.drawable.yellow_egg);
                }
                if (temID == R.id.blueEgg2) {
                    imgEgg[countEggs].setImageResource(R.drawable.blue_egg);
                }
                countEggs++;
            } else {
                soundControl.wrongSoundFun(main);
            }
            break;
        }
        case 5: {
            if (temID == R.id.grEgg5 || temID == R.id.grEgg6 || temID == R.id.purEgg1) {
                soundControl.correctSoundFun(main);
                butClick.setBackgroundResource(R.drawable.xsign);
                if (temID == R.id.purEgg1) {
                    imgEgg[countEggs].setImageResource(R.drawable.purple_egg);
                }
                if (temID == R.id.grEgg5 || temID == R.id.grEgg6) {
                    imgEgg[countEggs].setImageResource(R.drawable.green_egg);
                }
                countEggs++;
            } else {
                soundControl.wrongSoundFun(main);
            }
            break;
        }
        case 6: {
            if (temID == R.id.grEgg1 || temID == R.id.blueEgg1) {
                soundControl.correctSoundFun(main);
                butClick.setBackgroundResource(R.drawable.xsign);
                if (temID == R.id.blueEgg1) {
                    imgEgg[countEggs].setImageResource(R.drawable.blue_egg);
                }
                if (temID == R.id.grEgg1) {
                    imgEgg[countEggs].setImageResource(R.drawable.green_egg);
                }
                countEggs++;
            } else {
                soundControl.wrongSoundFun(main);
            }
            break;
        }
    }
}

//        if(countEggs == 6)
//        {
//            Winningactivity winningactivity = new Winningactivity();
//            soundControl.winSoundFun(main);
//
////
//                AlertDialog.Builder alerDialog = new AlertDialog.Builder(main);
//                alerDialog.setTitle("Thông báo!");
//                alerDialog.setIcon(R.drawable.xsign);
//                alerDialog.setMessage("Chúc mừng! Bạn đã chiến thắng");
//                alerDialog.setPositiveButton("Chơi lại", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//                alerDialog.setNegativeButton("Trang chủ", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//
//                alerDialog.show();

//        }
    }