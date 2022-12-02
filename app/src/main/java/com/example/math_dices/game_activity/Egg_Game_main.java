package com.example.math_dices.game_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.math_dices.HomePageActivity;
import com.example.math_dices.R;
import com.example.math_dices.controller.GameplayGame1;
import com.example.math_dices.controller.SoundControll;
import com.example.math_dices.controller.Utils;
import com.example.math_dices.firebase.Data_Controll;
import com.example.math_dices.sqlite.ArchivementDAO;
import com.example.math_dices.winning_activity.Winning_activity_egg;

import java.util.HashMap;
import java.util.Map;

public class Egg_Game_main extends AppCompatActivity {
    private int ID;
    Button replayBut;
    Button menuBut;
    public SoundControll soundControl = new SoundControll();
    GameplayGame1 gameOn = new GameplayGame1();
    /*
    image view
     */
    ImageView imgEgg1;
    ImageView imgEgg2;
    ImageView imgEgg3;
    ImageView imgEgg4;
    ImageView imgEgg5;
    ImageView imgEgg6;
    //
    ImageButton homeBut;
    ImageButton onoffBut;
    ImageButton diceBut;
    //* button
    Button grEgg1;
    Button grEgg2;
    Button grEgg3;
    Button grEgg4;
    Button grEgg5;
    Button grEgg6;
    Button grEgg7;
    Button redEgg1;
    Button redEgg2;
    Button redEgg3;
    Button yellowEgg1;
    Button yellowEgg2;
    Button purEgg1;
    Button purEgg2;
    Button blueEgg1;
    Button blueEgg2;
    //end egg
    ImageView imageSwitcher;
    int diceNumFinal ;
    //    ImageView birdnest[] = {imgEgg1,imgEgg3,imgEgg4,imgEgg5,imgEgg6,imgEgg2};//get id bird nests
    int ImageSwitcherImages[] ={R.drawable.game1_pic1};// create images for img switcher
    int switcherImageLength = ImageSwitcherImages.length;//length get
    Data_Controll data_controll;
    ArchivementDAO archivementDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1_main);
        getSupportActionBar().hide();
        archivementDAO = new ArchivementDAO(this);
        data_controll = new Data_Controll();
        Intent intent = getIntent();
        ID = intent.getIntExtra("uID",0); // get ID from previous
        int trophy = ((archivementDAO.returnTrophy(ID))); //lấy cúp theo ID
        imageSwitcher = findViewById(R.id.imgswt);
        homeBut = findViewById(R.id.homeBut);
        onoffBut = findViewById(R.id.SonoffBut);
        diceBut = findViewById(R.id.dice);
        //* img egg
        imgEgg1 = findViewById(R.id.ans1);
        imgEgg2 = findViewById(R.id.ans2);
        imgEgg3 = findViewById(R.id.ans3);
        imgEgg4 = findViewById(R.id.ans4);
        imgEgg5 = findViewById(R.id.ans5);
        imgEgg6 = findViewById(R.id.ans6);
        ImageView imgViews[] = {imgEgg1,imgEgg3,imgEgg4,imgEgg5,imgEgg6,imgEgg2};
        //get eggbut id

        grEgg1 = findViewById(R.id.grEgg1);
        grEgg2 = findViewById(R.id.grEgg2);
        grEgg3 = findViewById(R.id.grEgg3);
        grEgg4 = findViewById(R.id.grEgg4);
        grEgg5 = findViewById(R.id.grEgg5);
        grEgg6 = findViewById(R.id.grEgg6);
        grEgg7 = findViewById(R.id.grEgg7);
        redEgg1 = findViewById(R.id.redEgg1);
        redEgg2 = findViewById(R.id.redEgg2);
        redEgg3 = findViewById(R.id.redEgg3);
        yellowEgg1 = findViewById(R.id.yellowEgg1);
        yellowEgg2 = findViewById(R.id.yellowEgg2);
        purEgg1 = findViewById(R.id.purEgg1);
        purEgg2 = findViewById(R.id.purEgg2);
        blueEgg1 = findViewById(R.id.blueEgg1);
        blueEgg2 = findViewById(R.id.blueEgg2);

//        replayBut = findViewById(R.id.replayBut);
//        menuBut = findViewById(R.id.menuBut);
        // roll the dice
        diceBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int images[] = {R.drawable.dice_1,R.drawable.dice_2,R.drawable.dice_3,R.drawable.dice_4,R.drawable.dice_5,R.drawable.dice_6};
                int sec = 1;
                Utils.delay(sec, () -> {
                    for (int j = 0 ; j < 7;j++){
                        soundControl.RollSoundFun(view.getContext());
                        diceNumFinal = (int) (Math.random() * 6 + 1);
                        diceBut.setImageResource(images[diceNumFinal-1]);
                    }
                });
            }
        });
        // end roll
        /*
        start clicking the eggs
         */
        grEgg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameOn.gameOn(diceNumFinal,imgViews,grEgg1,Egg_Game_main.this);
                winAcOpen(trophy);
            }
        });
        grEgg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameOn.gameOn(diceNumFinal,imgViews,grEgg2,Egg_Game_main.this);
                winAcOpen(trophy);
            }
        });
        grEgg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameOn.gameOn(diceNumFinal,imgViews,grEgg3,Egg_Game_main.this);
                winAcOpen(trophy);
            }
        });
        grEgg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameOn.gameOn(diceNumFinal,imgViews,grEgg4,Egg_Game_main.this);
                winAcOpen(trophy);

            }
        });
        grEgg5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameOn.gameOn(diceNumFinal,imgViews,grEgg5,Egg_Game_main.this);
                winAcOpen(trophy);
            }
        });
        grEgg6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameOn.gameOn(diceNumFinal,imgViews,grEgg6,Egg_Game_main.this);
                winAcOpen(trophy);
            }
        });
        grEgg7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameOn.gameOn(diceNumFinal,imgViews,grEgg7,Egg_Game_main.this);
                winAcOpen(trophy);
            }
        });
        redEgg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameOn.gameOn(diceNumFinal,imgViews,redEgg1,Egg_Game_main.this);
                winAcOpen(trophy);
            }
        });
        redEgg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameOn.gameOn(diceNumFinal,imgViews,redEgg2,Egg_Game_main.this);
                winAcOpen(trophy);
            }
        });
        redEgg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameOn.gameOn(diceNumFinal,imgViews,redEgg3,Egg_Game_main.this);
                winAcOpen(trophy);
            }
        });
        yellowEgg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               gameOn.gameOn(diceNumFinal,imgViews,yellowEgg1,Egg_Game_main.this);
                winAcOpen(trophy);
            }
        });
        yellowEgg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               gameOn.gameOn(diceNumFinal,imgViews,yellowEgg2,Egg_Game_main.this);
                winAcOpen(trophy);
            }
        });
        purEgg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameOn.gameOn(diceNumFinal,imgViews,purEgg1,Egg_Game_main.this);
                winAcOpen(trophy);
            }
        });
        purEgg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameOn.gameOn(diceNumFinal,imgViews,purEgg2,Egg_Game_main.this);
                winAcOpen(trophy);
            }
        });
        blueEgg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameOn.gameOn(diceNumFinal,imgViews,blueEgg1,Egg_Game_main.this);
                winAcOpen(trophy);
            }
        });
        blueEgg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameOn.gameOn(diceNumFinal,imgViews,blueEgg2,Egg_Game_main.this);
                winAcOpen(trophy);
            }
        });

// end clicking the eggs
        // make view for image switcher
        imageSwitcher.setImageResource(R.drawable.game1_pic1);
        homeBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundControl.PopSoundFun(Egg_Game_main.this,homeBut);
                Intent intent = new Intent();
                intent.setClass(Egg_Game_main.this, HomePageActivity.class);
                intent.putExtra("uID",ID);
                startActivity(intent);
            }
        });
        imageSwitcher.setImageResource(ImageSwitcherImages[switcherImageLength-1]);
        soundControl.OnOffFun(Egg_Game_main.this,onoffBut);
    }
    // if eggs get ==6 -> open winning activity
    protected  void winAcOpen(int trophy){
        if(gameOn.countEggs == 6)
        {
            Animation bounce = AnimationUtils.loadAnimation(this, R.anim.bounce_animation);
            soundControl.hooraySoundFun(this);
            imgEgg1.setAnimation(bounce);
            imgEgg3.setAnimation(bounce);
            imgEgg5.setAnimation(bounce);
            Utils.delay(1, () -> {
                imgEgg2.setAnimation(bounce);
                imgEgg4.setAnimation(bounce);
                imgEgg6.setAnimation(bounce);
            });

            Utils.delay(4, () -> {
                // set new trophy archivement vào sqlite
                archivementDAO.settrophyByID(String.valueOf(trophy+1),ID);
                // set new trophy to firebase
                Map<String, Object> map = new HashMap<>();
                map.put("trophy",String.valueOf(trophy+1));
                data_controll.updateStringData(map,ID);
            });
            Utils.delay(10, () -> {
                Intent intent = new Intent();
                intent.setClass(Egg_Game_main.this, Winning_activity_egg.class);
                intent.putExtra("uID",ID);
                startActivity(intent);
            });


        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundControl.player.release();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        soundControl.player.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        soundControl.player.stop();
        soundControl.player.release();
    }
}