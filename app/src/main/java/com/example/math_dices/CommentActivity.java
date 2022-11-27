package com.example.math_dices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.math_dices.controller.CommentControll;
import com.google.android.material.textfield.TextInputLayout;

public class CommentActivity extends AppCompatActivity {
    private int ID;
    private EditText edt;
    private TextInputLayout txtly;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_dialog);
        getSupportActionBar().hide();
        edt = findViewById(R.id.cmtInput);
        btn = findViewById(R.id.cmtInBut);
        txtly = findViewById(R.id.txtlycmt);
        // lấy UID từ hoạt động trước
        Intent intent = getIntent();
        ID = intent.getIntExtra("uID",0);
        CommentControll controll = new CommentControll();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controll.send_Comment(edt,txtly,ID,view.getContext());
                String tem = edt.getText().toString().trim();
                if(tem.equals("") == false)
                {
                    Toast.makeText(view.getContext(),"Cảm ơn bạn đã đánh giá 🥳",Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(view.getContext(), HomePageActivity.class);
                    intent1.putExtra("uID",ID);
                    startActivity(intent1);
                }
                else
                {
                    Toast.makeText(view.getContext(),"Hãy nhập bình luận / đánh giá của bạn!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}