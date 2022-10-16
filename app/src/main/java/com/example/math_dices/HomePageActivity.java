package com.example.math_dices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.math_dices.controller.SoundControll;
import com.example.math_dices.sqlite.ArchivementDAO;

public class HomePageActivity extends AppCompatActivity {
    ImageButton actionMenuView;
    ImageButton card_gamebut;
    ImageButton soundbut;
    TextView trophynum;
    SoundControll soundControll = new SoundControll();

//    //thêm context menu -> long press
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//
//        super.onCreateContextMenu(menu, v, menuInfo);
//        if(v == actionMenuView)
//        {
//            menu.setHeaderTitle("Cài đặt");
//            menu.setHeaderIcon(R.drawable.setting_icon);
//            getMenuInflater().inflate(R.menu.menu_action,menu);
//        }
//    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().hide();
        //get id
        soundbut = findViewById(R.id.SonoffBut);
        card_gamebut = findViewById(R.id.chicken);
        trophynum = findViewById(R.id.trophynum);
        actionMenuView = findViewById(R.id.actionMenuView);

        soundControll.OnOffFun(HomePageActivity.this,soundbut);// chay nhac nen
        Intent intent = getIntent();
        int ID = intent.getIntExtra("uID",0);
        ArchivementDAO archivementDAO = new ArchivementDAO(this);
//        registerForContextMenu(actionMenuView);// gọi view vào menu button
        int trophy = archivementDAO.returnTrophy(ID);// lấy số cúp trong data
        // set cúp cho trophynum
        trophynum.setText(""+trophy);
        /*
        set chuc nang cho nut choi game
         */
        card_gamebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cmt = archivementDAO.returnCMT(ID);
                Toast.makeText(HomePageActivity.this,"số cúp "+trophy+"-"+cmt+"-",Toast.LENGTH_LONG).show();
            }
        });
        // set sự kiện show pop up menu
        actionMenuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup();
            }
        });
    }
    // sự kiện pop up menu
    private void showPopup()
    {
        PopupMenu popupMenu = new PopupMenu(this,actionMenuView);
        popupMenu.getMenuInflater().inflate(R.menu.menu_action,popupMenu.getMenu());
//         popupMenu.setForceShowIcon(true);
        popupMenu.show();
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