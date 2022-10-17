package com.example.math_dices.controller;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.math_dices.LoginActivity;
import com.example.math_dices.R;
import com.example.math_dices.sqlite.ArchivementDAO;
import com.example.math_dices.sqlite.UserDAO;

/*
class này dùng để quản lý sự kiện dialog khi click vao items trong pop up menu setting
 */
public class SettingDialog {
    String name;
    int trophy;
    String dob;
    /*
    khi click vao logout but trong pop up menu
     */
    public void logout_func(Context context)
    {
        // su kien alert dialog
        AlertDialog.Builder b = new AlertDialog.Builder(context);
        b.setTitle("Đăng xuất");
        b.setIcon(R.drawable.log_out);
        b.setMessage("Bạn muốn đăng xuất khỏi tài khoản hiện tại ?");
        b.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel(); // huy dialog
            }
        });
        b.setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            }
        });
        b.create().show();// show dialog
    }
    /*
    khi click vào người dùng sẽ show ra dialog người dùng
     */
    public void user_setting(Dialog dialog,Context context,int id)
    {
        UserDAO userDAO = new UserDAO(context);
        ArchivementDAO archivementDAO = new ArchivementDAO(context);

        dialog.setContentView(R.layout.activity_user_interface);
        TextView txtname = dialog.findViewById(R.id.profile_uname);
        TextView txtdob = dialog.findViewById(R.id.txtdob);
        TextView txttrophy = dialog.findViewById(R.id.txttrophy);
        ImageButton imgbut;
        Button imgbut2 = dialog.findViewById(R.id.profile_fix);
        imgbut = dialog.findViewById(R.id.xBut);
        // tim data theo id
            name = userDAO.returnname(id);
            trophy = archivementDAO.returnTrophy(id);
            dob = userDAO.returndob(id);
        txtname.setText("" +name);
        txtdob.setText(dob);
        txttrophy.setText(""+trophy);
        imgbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        // chức năng chỉnh sửa thông tin
        imgbut2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // su kien alert dialog
                AlertDialog.Builder b = new AlertDialog.Builder(context);
                b.setTitle("Chỉnh sửa thông tin");
                b.setIcon(R.drawable.log_out);
                b.setMessage("Bạn muốn chỉnh sửa thông tin cá nhân ?");
                b.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel(); // huy dialog
                    }
                });
                b.setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.setContentView(R.layout.fix_user_profile);
                        EditText tx1,tx2;
                        TextView tx3;
                        tx1 = dialog.findViewById(R.id.edtprofile_uname);
                        tx2 = dialog.findViewById(R.id.edtdob);
                        tx3 = dialog.findViewById(R.id.txttrophy);
                        tx1.setText(name);
                        tx2.setText(dob);
                        tx3.setText(""+trophy);
                        ImageButton butx = dialog.findViewById(R.id.xBut);
                        Button done = dialog.findViewById(R.id.fix_done);
                        butx.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        // chấp nhận chỉnh sửa thông tin xong
                        done.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // su kien alert dialog
                                AlertDialog.Builder b = new AlertDialog.Builder(context);
                                b.setTitle("Hoàn tất");
                                b.setIcon(R.drawable.log_out);
                                b.setMessage("Hoàn tất cập nhật thông tin?");
                                b.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel(); // huy dialog
                                    }
                                });
                                // đồng ý chỉnh sửa thì gửi data đi
                                b.setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        EditText edt1,edt2;
                                        edt1 = dialog.findViewById(R.id.edtprofile_uname);
                                        edt2 = dialog.findViewById(R.id.edtdob);
                                        dialog.setContentView(R.layout.activity_user_interface);
                                        TextView txt1,txt2,txt3;
                                        txt2 = dialog.findViewById(R.id.txtdob);
                                        txt1 = dialog.findViewById(R.id.profile_uname);
                                        txt3 = dialog.findViewById(R.id.txttrophy);
                                        txt1.setText(edt1.getText().toString());
                                        txt2.setText(edt2.getText().toString());
                                        txt3.setText(""+trophy);
                                        ImageButton butx = dialog.findViewById(R.id.xBut);
                                        butx.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                dialog.dismiss();
                                            }
                                        });
                                    }
                                });
                                b.create().show();// show dialog
                            }
                        });
                    }
                });
                b.create().show();// show dialog
            }
        });
        dialog.show();
    }
    /*
    khi click vào bảng xếp hạng
     */
    public void total_setting(Context context)
    {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
