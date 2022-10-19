package com.example.math_dices.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
//import android.support.design.widget.TextInputLayout;
//import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.math_dices.LoginActivity;
import com.example.math_dices.R;
import com.example.math_dices.adapter.ArchivementAdapter;
import com.example.math_dices.firebase.Data_Controll;
import com.example.math_dices.firebase.Send_Data_User;
import com.example.math_dices.model.TotalArchivement;
import com.example.math_dices.sqlite.ArchivementDAO;
import com.example.math_dices.sqlite.UserDAO;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/*
class này dùng để quản lý sự kiện dialog khi click vao items trong pop up menu setting
 */
public class SettingDialog {
    private String name;
    private int trophy;
    private String dob;
    private String pass;
    private Data_Controll data_controll = new Data_Controll();

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
        Button changpass = dialog.findViewById(R.id.pass_fix);
        imgbut = dialog.findViewById(R.id.xBut);
        // tim data theo id
            name = userDAO.returnname(id);
            trophy = archivementDAO.returnTrophy(id);
            dob = userDAO.returndob(id);
            pass = userDAO.returnPass_ID(id);
        txtname.setText("" +name);
        txtdob.setText(dob);
        txttrophy.setText(""+trophy);
        // nút thoát
        xButOut(imgbut,dialog);
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
                        xButOut(butx,dialog);
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
                                        txt1.setText(name);
                                        txt2.setText(dob);
                                        boolean check = userDAO.checkname(edt1.getText().toString(),id);
                                        if(check == true)
                                        {
                                            Toast.makeText(context,"Tên này đã có người dùng",Toast.LENGTH_SHORT).show();
                                            edt1.setError("Lỗi");
                                            dialog.dismiss();
                                        }
                                        else
                                        {
                                            Map<String,Object> map = new HashMap<>();
                                            txt1.setText(edt1.getText().toString());
                                            txt2.setText(edt2.getText().toString());
                                            txt3.setText(""+trophy);
                                            userDAO.setNameByID(edt1.getText().toString(),id); // set dữ liệu vào data
                                            userDAO.setDobByID(edt2.getText().toString(),id);// set dob vào data
                                            map.put("name",edt1.getText().toString());// gui len firebase
                                            map.put("dob",edt2.getText().toString());// gui len firebase
                                            data_controll.updateStringData(map,id);// chap nhan gui
                                            Toast.makeText(context,"Cập nhật thông tin thành công",Toast.LENGTH_SHORT).show();

                                        }
                                        ImageButton butx = dialog.findViewById(R.id.xBut);
                                        xButOut(butx,dialog);
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
        // chuyển qua view đổi mật khẩu
        changpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.change_pass);
                TextView txtname = dialog.findViewById(R.id.profile_uname);
                TextView txtdob = dialog.findViewById(R.id.txtdob);
                EditText edt1 = dialog.findViewById(R.id.oldpass);
                EditText edt2 = dialog.findViewById(R.id.newpass);
                EditText edt3 = dialog.findViewById(R.id.renewpass);
                TextInputLayout til1 = dialog.findViewById(R.id.til1);
                TextInputLayout til2 = dialog.findViewById(R.id.til2);
                TextInputLayout til3 = dialog.findViewById(R.id.til3);
                ImageButton butx = dialog.findViewById(R.id.xBut);
                Button chang_But = dialog.findViewById(R.id.changepassBut);
                xButOut(butx,dialog);
                txtdob.setText(dob);
                txtname.setText(name);
                chang_But.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        change_pass(view.getContext(),til1,til2,til3,edt1,edt2,edt3,dialog,id);
                    }
                });

            }
        });
        dialog.show();
    }
    /*
    khi click vào bảng xếp hạng
     */
    public void total_setting(Dialog dialog,Context context,int idin)
    {
        dialog.setContentView(R.layout.archivement_total);
        ArrayList<TotalArchivement> listProduct = new ArrayList<>();
        ArchivementAdapter adapter;
        ListView listViewArchive;
        listViewArchive = dialog.findViewById(R.id.list_item);
        UserDAO userDAO = new UserDAO(context);
        userDAO.getArchive(listProduct);// lấy data vào arraylist
        adapter = new ArchivementAdapter(listProduct);// khởi tạo adapter
        name = userDAO.returnname(idin);
        adapter.id = idin; // id của người dùng hiện tại
        Send_Data_User send = new Send_Data_User(); // lấy dữ liệu từ firebase

        listViewArchive.setAdapter(adapter);
        send.updateADTData(context,adapter);// gọi sự kiện update adapter
        ImageButton xbut = dialog.findViewById(R.id.xBut);
        Button maxBut = dialog.findViewById(R.id.sort1);
        Button minBut = dialog.findViewById(R.id.sort3);
        xButOut(xbut,dialog);
        // sort dữ liệu theo nhiều cup nhất
        maxBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listProduct.sort((Comparator.comparingDouble(TotalArchivement::getTrophy).reversed()));
                for(int i=0;i<listProduct.size();i++) // cập nhật lại thứ tự ID trong arraylist
                {
                    listProduct.get(i).setStt(i+1);
                }
                adapter.notifyDataSetChanged();
            }
        });
        // sort dữ liệu theo ít cúp nhất
        minBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listProduct.sort((Comparator.comparingDouble(TotalArchivement::getTrophy)));
                for(int i=0;i<listProduct.size();i++) // cập nhật lại thứ tự ID
                {
                    listProduct.get(i).setStt(i+1);
                }
                adapter.notifyDataSetChanged();
            }
        });
        dialog.show();
    }

    /*
    chuc nang nút đóng x
     */
    public void xButOut(ImageButton xBut,Dialog dialog)
    {
        xBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
    /*
    chuc nang doi mat khẩu
     */
    public void change_pass(Context context, TextInputLayout til1, TextInputLayout til2, TextInputLayout til3, EditText edt1, EditText edt2, EditText edt3, Dialog dialog, int id)
    {
        String edtin1 = edt1.getText().toString();
        String edtin2 = edt2.getText().toString();
        String edtin3 = edt3.getText().toString();

        if(edtin1.equals(""))
        {
            Toast.makeText(context,"Hãy nhập hết các trường dữ liệu",Toast.LENGTH_SHORT).show();
            til1.setError("Không được để trống");
            til2.setErrorEnabled(false);
            til3.setErrorEnabled(false);
        }
        else if(edtin2.equals(""))
        {
            Toast.makeText(context,"Hãy nhập hết các trường dữ liệu",Toast.LENGTH_SHORT).show();
            til2.setError("Không được để trống");
            til1.setErrorEnabled(false);
            til3.setErrorEnabled(false);
        }
        else if(edtin3.equals(""))
        {
            Toast.makeText(context,"Hãy nhập hết các trường dữ liệu",Toast.LENGTH_SHORT).show();
            til3.setError("Không được để trống");
            til2.setErrorEnabled(false);
            til1.setErrorEnabled(false);
        }
        else
        {
            if(edtin1.equals(pass) == false) // mật khẩu cũ không trùng
            {
                til1.setErrorEnabled(true);
                til1.setError("Mật khẩu cũ sai");
                Toast.makeText(context,"Mật khẩu cũ không chính xác",Toast.LENGTH_SHORT).show();
            }
            else // nếu trùng thì xử lý tiếp
            {
                til1.setErrorEnabled(false);
                if(edtin2.equals(edtin3) == true) // nếu mk mới và nhập lại đúng
                {
                    if(edtin2.length()<=6) // mật khẩu nhập phải lớn hơn 6 ký tự
                    {
                        Toast.makeText(context,"Mật khẩu mới phải dài hơn 6 ký tự",Toast.LENGTH_SHORT).show();
                    }
                    if(edtin2.equals(edtin1)==true) // mật khẩu mới phải khác mật khẩu cũ
                    {
                        Toast.makeText(context,"Mật khẩu mới phải khác mật khẩu cũ",Toast.LENGTH_SHORT).show();
                    }
                    if(edtin2.length()>6 && edtin2.equals(edtin1) == false) // thỏa mãn tất cả điều kiện
                    {
                        UserDAO userDAO = new UserDAO(context);
                        Map<String, Object> map = new HashMap<>();
                        Data_Controll data_controll = new Data_Controll();
                        map.put("password",edtin2);
                        data_controll.updateStringData(map,id);
                        userDAO.setNewPassByID(edtin2,id);
                        Toast.makeText(context,"Thay đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }

                }
                else
                {
                    til2.setErrorEnabled(true);
                    til3.setErrorEnabled(true);
                    til2.setError("Không trùng nhau");
                    til3.setError("Không trùng nhau");
                    Toast.makeText(context,"Mật khẩu mới được nhập lại không trùng",Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}
