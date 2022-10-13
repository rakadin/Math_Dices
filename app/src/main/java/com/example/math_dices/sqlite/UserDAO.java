package com.example.math_dices.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.math_dices.database.AppData;
import com.example.math_dices.model.Users;

public class UserDAO {
    private SQLiteDatabase db;

    public UserDAO(Context context) { // phải truyền context vào
        AppData appData = new AppData(context); // khai bao de ket noi
        this.db = appData.getWritableDatabase();
    }
    public long insertUser(Users users) // them user mới -> đăng ký
    {
        ContentValues values = new ContentValues();// tạo values
        values.put("name", users.getName());
        values.put("username", users.getUsername());
        values.put("password", users.getPassword());
        values.put("dob", "00/00/0000");
        return  db.insert("User",null, values);// gửi value kia vào database

    }
    public boolean checkuser(String s)
    {
        Cursor cursor =  db.rawQuery("SELECT * FROM User where username = ?", new String[]{String.valueOf(s)});
        if(cursor.getCount() == 0)
        {
            return false;

        }
        else return true;

    }
//    public int showuserID(String s)
//    {
//        Cursor cursor =  db.rawQuery("SELECT * FROM User where username = ?", new String[]{String.valueOf(s)});
//        cursor.isNull();
//        int id = cursor.getInt(0);
//        return id;
//    }
}
