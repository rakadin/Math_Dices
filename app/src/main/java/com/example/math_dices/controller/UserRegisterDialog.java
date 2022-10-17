package com.example.math_dices.controller;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.Toast;

import com.example.math_dices.LoginActivity;
import com.example.math_dices.model.Archivement;
import com.example.math_dices.model.Users;
import com.example.math_dices.sqlite.ArchivementDAO;
import com.example.math_dices.sqlite.UserDAO;

import java.util.Date;

public class UserRegisterDialog {

    public void checkuser(EditText usname, Context context, EditText pass, EditText repass, TextInputLayout til1,TextInputLayout til2,TextInputLayout til3){
        Users users = new Users();
        Archivement archivement = new Archivement();
        String user =  usname.getText().toString();
        String pass1 =  pass.getText().toString();
        String repass1 =  repass.getText().toString();
        UserDAO userDAO = new UserDAO(context);
        ArchivementDAO archivementDAO = new ArchivementDAO(context);

        // đảm bảo các trường được nhập
        if(user.equals(""))
        {
            til1.setError("Không được bỏ trống");
            til2.setErrorEnabled(false);
            til3.setErrorEnabled(false);
        }
        else if(pass1.equals(""))
        {
            til1.setErrorEnabled(false);
            til2.setError("Không được để trống!");
            til3.setErrorEnabled(false);
        }
        else if(repass1.equals(""))
        {
            til3.setError("Không được để trống!");
            til1.setErrorEnabled(false);
            til2.setErrorEnabled(false);
        }
        else // nếu các trường đã được nhập hết
        {
            // kiem tra username ton tai chua
            if (userDAO.checkuser(user) == true)
            {
                Toast.makeText(context,"Tài khoản đã tồn tại !",Toast.LENGTH_SHORT).show();
            }
            else
            {
                // kiểm tra repass giống k
                if(pass1.equals(repass1))
                {
                    if(pass1.length()<=6)// giống nma mật khẩu phải lớn hơn 6 ký tự
                    {
                        Toast.makeText(context,"Mật khẩu phải dài hơn 6 ký tự !",Toast.LENGTH_LONG).show();
                    }
                    else
                    { // đăng ký thành công
                        // set du lieu cho user moi
                        users.setUsername(user);
                        users.setDob("00/00/0000");
                        users.setName(user);
                        users.setPassword(pass1);
                        userDAO.insertUser(users);
                        // them archive khi khởi tạo
                        int id = userDAO.returnID(user);
                        archivement.setuID(id);
                        archivement.setTrophy(0);
                        archivement.setCmt("");
                        archivementDAO.insertArchive(archivement);
                        // thông báo
                        Toast.makeText(context,"Tạo tài khoản thành công",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent); // mở trang đăng nhập để đăng nhập
                    }

                }
                else
                {
                    Toast.makeText(context,"Mật khẩu không trùng khớp !",Toast.LENGTH_SHORT).show();
                }
        }


        }
    }
}
