package com.example.math_dices.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.math_dices.database.AppData;
import com.example.math_dices.model.Archivement;
import com.example.math_dices.model.Users;

public class ArchivementDAO {
    private SQLiteDatabase DB;

    public ArchivementDAO(Context context) {
        AppData appData = new AppData(context); // khai bao de ket noi
        this.DB = appData.getWritableDatabase();
    }
    /*
   thêm archive mới nào DB khi user được tạo
    */
    public long insertArchive(Archivement ar) // them user mới -> đăng ký
    {
        ContentValues values = new ContentValues();// tạo values
        values.put("uID", ar.getuID());
        values.put("trophy", ar.getTrophy());
        values.put("comment", ar.getCmt());
        return  DB.insert("Archivement",null, values);// gửi value kia vào database

    }
    /*
  trả về trophy theo id
   */
    public int returnTrophy(int id)
    {
        Cursor cursor =  DB.rawQuery("SELECT trophy FROM Archivement where uID = ?", new String[]{String.valueOf(id)});
        cursor.moveToNext();
        int trophy = cursor.getInt(0);
        return trophy;
    }
    /*
    trả về comment theo id
    */
    public String returnCMT(int id)
    {
        Cursor cursor =  DB.rawQuery("SELECT comment FROM Archivement where uID = ?", new String[]{String.valueOf(id)});
        cursor.moveToNext();
        String cmt = cursor.getString(0);
        return cmt;
    }
}
