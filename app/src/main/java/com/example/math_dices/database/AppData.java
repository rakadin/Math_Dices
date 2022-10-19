package com.example.math_dices.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//import android.support.annotation.Nullable;
/*
kết nối với sqlite database
 */
public class AppData extends SQLiteOpenHelper {
    public static final String DB_Name = "MathDice";
    public static final int version = 1 ;
    // constructor
    public AppData(@Nullable Context context) {
        super(context, DB_Name, null, version);
    }

    // tạo table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String Usersql = "CREATE TABLE User(id INTEGER PRIMARY KEY AUTOINCREMENT not null, username text not null, password text not null,name NVARCHAR(50) , dob CHAR(10)  );";
        String Archivesql = "CREATE TABLE Archivement(uID INTEGER, trophy INTEGER, comment NVARCHAR,FOREIGN KEY(uID) REFERENCES User(id))";
        sqLiteDatabase.execSQL(Usersql);
        sqLiteDatabase.execSQL(Archivesql);
    }
    // cập nhật/ nâng cấp csdl
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS User";
        String sql2 = "DROP TABLE IF EXISTS Archivement";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql2);
        onCreate(sqLiteDatabase);
    }
}
