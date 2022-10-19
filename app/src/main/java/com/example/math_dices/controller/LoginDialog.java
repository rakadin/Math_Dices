package com.example.math_dices.controller;

import android.content.Context;
import android.content.Intent;
//import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.Toast;

import com.example.math_dices.HomePageActivity;
import com.example.math_dices.LoginActivity;
import com.example.math_dices.model.Archivement;
import com.example.math_dices.model.Users;
import com.example.math_dices.sqlite.ArchivementDAO;
import com.example.math_dices.sqlite.UserDAO;
import com.google.android.material.textfield.TextInputLayout;

public class LoginDialog {
    public void validate(EditText usname, Context context, EditText pass, TextInputLayout til1, TextInputLayout til2)
    {
        Users users = new Users();
        String user =  usname.getText().toString().trim();
        String pass1 =  pass.getText().toString().trim();

        // gọi data
        UserDAO userDAO = new UserDAO(context);
        // đảm bảo các trường được nhập
        if(user.equals(""))
        {
            til1.setError("Không được bỏ trống");
            til2.setErrorEnabled(false);
        }
        else if(pass1.equals(""))
        {
            til1.setErrorEnabled(false);
            til2.setError("Không được để trống!");
        }
        else // nếu các trường đã được nhập hết
        {
            // kiem tra username ton tai chua
            if (userDAO.checkuser(user) == true)// có tài khoản
            {
                int id = userDAO.returnID(user);
                String valiPass = userDAO.returnPass(user);
                if(pass1.equals(valiPass) == true) // mật khẩu đúng -> đăng nhập thành công
                {
                    Toast.makeText(context,"Đăng nhập thành công !",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, HomePageActivity.class);
                    intent.putExtra("uID",id);
                    context.startActivity(intent);
                }
                else // nếu pass không đúng
                {
                    Toast.makeText(context,"Tên đăng nhập hoặc mật khẩu không đúng !",Toast.LENGTH_SHORT).show();

                }
            }
            else
            {
                Toast.makeText(context,"Tên đăng nhập hoặc mật khẩu không đúng !",Toast.LENGTH_SHORT).show();
            }


        }
    }
}
