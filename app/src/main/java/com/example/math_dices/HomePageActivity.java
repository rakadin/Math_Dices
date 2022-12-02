package com.example.math_dices;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.ActionMenuView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

//import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.math_dices.controller.CommentControll;
import com.example.math_dices.controller.SettingDialog;
import com.example.math_dices.controller.SoundControll;
import com.example.math_dices.firebase.Data_Controll;
import com.example.math_dices.firebase.Send_Data_User;
import com.example.math_dices.introductions.Card_introduction;
import com.example.math_dices.introductions.Chicken_introdiction;
import com.example.math_dices.introductions.Egg_Game_Introduction;
import com.example.math_dices.introductions.Fish_game_introduction;
import com.example.math_dices.introductions.Slide_introduction;
import com.example.math_dices.sqlite.ArchivementDAO;
import com.example.math_dices.sqlite.UserDAO;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class HomePageActivity extends AppCompatActivity {
    int ID;
    ImageButton actionMenuView;
    ImageButton card_gamebut,slide_game_but,eggGameBut,fishgamebut,chickengamebut;
    ImageButton soundbut;
    SoundControll soundControll = new SoundControll();
    Dialog dialog,dialogCMt;// tham số mở pop up view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().hide();
        Send_Data_User send = new Send_Data_User();
        send.getNewIDData(this); // lấy dữ liệu theo thời gian thực
        dialog = new Dialog(this);
        dialogCMt =  new Dialog(this);
        //get id
        soundbut = findViewById(R.id.SonoffBut);
        chickengamebut = findViewById(R.id.cardch);
        card_gamebut = findViewById(R.id.chicken);
        actionMenuView = findViewById(R.id.actionMenuView);
        slide_game_but = findViewById(R.id.slide);
        eggGameBut = findViewById(R.id.eggcatch);
        fishgamebut = findViewById(R.id.fishcatch);
        soundControll.OnOffFun(HomePageActivity.this,soundbut);// chay nhac nen
        // lấy UID từ hoạt động trước
        Intent intent = getIntent();
        ID = intent.getIntExtra("uID",0);
        ArchivementDAO archivementDAO = new ArchivementDAO(this);
        int trophy = archivementDAO.returnTrophy(ID);// lấy chỉ số cúp hiện tại theo UID
        String cmt = archivementDAO.returnCMT(ID);
//        registerForContextMenu(actionMenuView);// gọi view vào menu button

        /*
        set chuc nang cho nut choi game
         */
        card_gamebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Card_introduction.class);
                intent.putExtra("uID",ID); //gui ID
                startActivity(intent);
            }
        });
        slide_game_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Slide_introduction.class);
                intent.putExtra("uID",ID);// gui ID
                startActivity(intent);
            }
        });
        eggGameBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Egg_Game_Introduction.class);
                intent.putExtra("uID",ID); //gui ID
                startActivity(intent);
            }
        });
        fishgamebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Fish_game_introduction.class);
                intent.putExtra("uID",ID); //gui ID
                startActivity(intent);
            }
        });
        chickengamebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Chicken_introdiction.class);
                intent.putExtra("uID",ID); //gui ID
                startActivity(intent);
            }
        });
        // set sự kiện show pop up menu
        actionMenuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup();
            }
        });
        // sự kiện hiện hộp chấp nhận đánh giá hay không
        if( (trophy % 5) == 0 && cmt.trim().equals("") == true && trophy >4)
        {
                CommentControll controll = new CommentControll();
                controll.commentDialog(dialogCMt,this,trophy,ID);
        }
    }
    // sự kiện pop up menu
    private void showPopup()
    {
        SettingDialog settingDialog = new SettingDialog();
        PopupMenu popupMenu = new PopupMenu(this,actionMenuView);
        popupMenu.getMenuInflater().inflate(R.menu.menu_action,popupMenu.getMenu());
//         popupMenu.setForceShowIcon(true);
        Context context = HomePageActivity.this; // khai báo để truyền context vào trong
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.usersetting:
                        settingDialog.user_setting(dialog,context,ID);
                        break;
                    case R.id.total:
                    {
                        settingDialog.total_setting(dialog,context,ID);
                        break;
                    }

                    case R.id.logout:
                        settingDialog.logout_func(context);
                        break;
                }
                return false;
            }
        });
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