package com.example.math_dices.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.math_dices.CommentActivity;
import com.example.math_dices.HomePageActivity;
import com.example.math_dices.LoginActivity;
import com.example.math_dices.R;
import com.example.math_dices.firebase.Data_Controll;
import com.example.math_dices.sqlite.ArchivementDAO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;
// có thể bỏ extend đi -> k cần nó

public class CommentControll extends AppCompatActivity {
    public void commentDialog(Dialog dialog, Context context,int trophy, int ID)
    {
//        Toast.makeText(context,"trophy in "+trophy,Toast.LENGTH_LONG).show();
        // su kien alert dialog
        AlertDialog.Builder b = new AlertDialog.Builder(context);
        b.setIcon(R.drawable.icon_cmt);
        b.setTitle("Đánh giá / Bình luận");
        b.setMessage("  Chào bạn, sau khi trải nghiệm trò chơi này một thời gian thì bạn có đánh giá hay nhận xét gì cho trò chơi này của chúng tôi không ?. Hãy bỏ ra một chút thời gian để chúng tôi có thể biết được trò chơi chúng tôi đang phát triển cần nâng cấp hoặc sửa chữa gì không nhé. Chúng tôi rất cảm kích khi bạn tham gia đánh giá đó nha \uD83D\uDE01\uD83E\uDD29\uD83D\uDE0D. Mãi yêu\uD83E\uDD73");
        b.setPositiveButton("Đánh giá luôn", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(context, CommentActivity.class);
                intent.putExtra("uID",ID);
                context.startActivity(intent);
            }
        });
        b.setNegativeButton("Lúc khác nhé", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel(); // huy dialog
            }
        });
        b.create().show();// show dialog
    }
    public void send_Comment(EditText edt, TextInputLayout textInputLayout,int ID,Context context)
    {
        String cmt = edt.getText().toString();
        cmt.trim();
        if(cmt.equals("") == true)
        {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("Nhập bình luận của bạn vào đi nào 😉");

        }
        else
        {
            ArchivementDAO archivementDAO = new ArchivementDAO(context);
            textInputLayout.setErrorEnabled(false);
            Map<String, Object> map = new HashMap<>();
            Data_Controll data_controll = new Data_Controll();
            map.put("comment",cmt);
            data_controll.updateStringData(map,ID); // gửi cmt lên firebase theo ID
            archivementDAO.setcmtByID(cmt,ID);// lưu vào sqlite
        }

    }
}
