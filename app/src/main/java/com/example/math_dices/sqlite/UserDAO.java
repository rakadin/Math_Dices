package com.example.math_dices.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.math_dices.database.AppData;
import com.example.math_dices.model.Users;

public class UserDAO {
    private SQLiteDatabase db;

    // khởi tạo trường user
    public UserDAO(Context context) { // phải truyền context vào
        AppData appData = new AppData(context); // khai bao de ket noi
        this.db = appData.getWritableDatabase();
    }
    /*
    thêm user mới nào DB
     */
    public long insertUser(Users users) // them user mới -> đăng ký
    {
        ContentValues values = new ContentValues();// tạo values
        values.put("name", users.getName());
        values.put("username", users.getUsername());
        values.put("password", users.getPassword());
        values.put("dob", "00/00/0000");
        return  db.insert("User",null, values);// gửi value kia vào database

    }
    /*
    validate user đã có chưa
     */
    public boolean checkuser(String s)
    {
        Cursor cursor =  db.rawQuery("SELECT * FROM User where username = ?", new String[]{String.valueOf(s)});
        if(cursor.getCount() == 0)
        {
            return false;

        }
        else return true;

    }
    /*
    trả về ID theo username
     */
    public int returnID(String s)
    {
        Cursor cursor =  db.rawQuery("SELECT id FROM User where username = ?", new String[]{String.valueOf(s)});
        cursor.moveToNext();
        int id = cursor.getInt(0);
        return id;
    }
    /*
    trả về password theo username
     */
    public String returnPass(String s)
    {
        Cursor cursor =  db.rawQuery("SELECT password FROM User where username = ?", new String[]{String.valueOf(s)});
        cursor.moveToNext();
        String pass = cursor.getString(0);
        return pass;
    }
    /*
  trả về name theo id
   */
    public String returnname(int s)
    {
        Cursor cursor =  db.rawQuery("SELECT name FROM User where id = ?", new String[]{String.valueOf(s)});
        cursor.moveToNext();
        String name = cursor.getString(0);
        return name;
    }
    /*
trả về ngay sinh theo id
*/
    public String returndob(int s)
    {
        Cursor cursor =  db.rawQuery("SELECT dob FROM User where id = ?", new String[]{String.valueOf(s)});
        cursor.moveToNext();
        String name = cursor.getString(0);
        return name;
    }
    /*
trả về pass theo id
*/
    public String returnPass_ID(int s)
    {
        Cursor cursor =  db.rawQuery("SELECT password FROM User where id = ?", new String[]{String.valueOf(s)});
        cursor.moveToNext();
        String name = cursor.getString(0);
        return name;
    }
    /*
    set name by id
     */
    public void setNameByID(String name,int id)
    {
        db.execSQL("UPDATE User SET name = ? where id = ?", new String[]{name,String.valueOf(id)});
    }
    /*
  set date of birth by id
   */
    public void setDobByID(String name,int id)
    {
        db.execSQL("UPDATE User SET dob = ? where id = ?", new String[]{name,String.valueOf(id)});
    }
    /*
    set mật khẩu mới bởi id
     */
    public void setNewPassByID(String pass,int id)
    {
        db.execSQL("UPDATE User SET password = ? where id = ?", new String[]{pass,String.valueOf(id)});
    }
}
