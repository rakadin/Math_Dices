package com.example.math_dices.game_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.math_dices.HomePageActivity;
import com.example.math_dices.R;
import com.example.math_dices.controller.SoundControll;
import com.example.math_dices.controller.Utils;
import com.example.math_dices.firebase.Data_Controll;
import com.example.math_dices.sqlite.ArchivementDAO;
import com.example.math_dices.winning_activity.Winning_activity_card;

import java.util.HashMap;
import java.util.Map;

public class Card_game_main extends AppCompatActivity {
    ImageButton diceBut;
    SoundControll soundControl = new SoundControll();
    int diceNumFinal;
    int card_cound = 0;
    ImageButton card1;
    ImageButton card2;
    ImageButton card3;
    ImageButton card4;
    ImageButton card5;
    ImageButton card6;
    ImageButton card7;
    ImageButton card8;
    ImageButton card9;
    ImageButton card10;
    ImageButton card11;
    ImageButton card12;

    ImageButton onoffBut;
    ImageButton homeBut;
    Data_Controll data_controll;
    ArchivementDAO archivementDAO;

    TextView total;
    private int ID,trophy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_game_main);
        getSupportActionBar().hide();
        archivementDAO = new ArchivementDAO(this);
        data_controll = new Data_Controll();
        // get id from previous
        Intent intent = getIntent();
        ID = intent.getIntExtra("uID",0); // get ID from previous
        trophy = ((archivementDAO.returnTrophy(ID))); //lấy cúp theo ID
        // get id
        diceBut = findViewById(R.id.dice);
        // card id
        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);
        card5 = findViewById(R.id.card5);
        card6 = findViewById(R.id.card6);
        card7 = findViewById(R.id.card7);
        card8 = findViewById(R.id.card8);
        card9 = findViewById(R.id.card9);
        card10 = findViewById(R.id.card10);
        card11 = findViewById(R.id.card11);
        card12 = findViewById(R.id.card12);
        total = findViewById(R.id.total);
        // animation
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.card_animation);
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.card_animation_wrong);
        onoffBut = findViewById(R.id.SonoffBut_game2);
        homeBut = findViewById(R.id.homeBut);
        ImageButton CARDS[] = {card1,card2,card3,card4,card5,card6,card7,card8,card9,card10,card11,card12};
        int cards_value[] = {1,1,2,2,3,3,4,4,5,5,6,6};
        /*
        dice roll
         */
        diceBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int images[] = {R.drawable.dice_1,R.drawable.dice_2,R.drawable.dice_3,R.drawable.dice_4,R.drawable.dice_5,R.drawable.dice_6};
                int sec = 1;
                for (int j = 0 ; j < 7;j++){
                    Utils.delay(sec, () -> {
                        soundControl.RollSoundFun(Card_game_main.this);
                        diceNumFinal = (int) (Math.random() * 6 + 1);
//                        question.setText(" "+diceNumFinal);
                        diceBut.setImageResource(images[diceNumFinal-1]);
                    });
                }

            }
        });
        /*
        end roll
         */
        for (int i = 0 ; i < 12;i++)
        {
            int tem = i;
            CARDS[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(cards_value[tem] == diceNumFinal)
                    {
                        CARDS[tem].startAnimation(animation);
                        soundControl.correctSoundFun(Card_game_main.this);
                        // controll yellow and blue card to flip back
                        if (tem % 2 == 0) {
                            CARDS[tem].setImageResource(R.drawable.yellow_card_back);
                        }
                        else
                        {
                            CARDS[tem].setImageResource(R.drawable.blue_card_back);
                        }
                        Toast.makeText(Card_game_main.this,"Đúng rồi !!!",Toast.LENGTH_LONG).show();
                        card_cound++;

                        total.setText("Total: "+card_cound);// hiển thị số thẻ đã đạt được

                        // if card count == 6 -> win
                        if(card_cound == 6)
                        {
                            Animation blink = AnimationUtils.loadAnimation(view.getContext(), R.anim.blink_animation);
                            soundControl.hooraySoundFun(Card_game_main.this);
                            total.setAnimation(blink);
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
                                intent.setClass(view.getContext(), Winning_activity_card.class);
                                intent.putExtra("uID",ID);
                                startActivity(intent);
                            });
//                            soundControl.PopSoundFun(view.getContext(),homeBut);

                        }
                    }
                    else
                    {
                        CARDS[tem].startAnimation(animation2);
                        soundControl.wrongSoundFun(Card_game_main.this);
                        Toast.makeText(Card_game_main.this,"Sai rồi !!!",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        // sound controll but
        soundControl.OnOffFun(Card_game_main.this,onoffBut);
        // home navigate button
        homeBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundControl.PopSoundFun(view.getContext(),homeBut);
                Intent intent = new Intent();
                intent.setClass(view.getContext(), HomePageActivity.class);
                intent.putExtra("uID",ID);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundControl.player.stop();
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
    }
}