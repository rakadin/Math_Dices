package com.example.math_dices.firebase;

import android.content.Context;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.math_dices.adapter.ArchivementAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Send_Data_User {
    public boolean chk = false;
    // đăng ký người dùng mới với firebase
    public void registerNewUser(String usname, String name, String pass,String dob,String cmt,int trophy,int ID)
    {
        Data_Controll data_controll = new Data_Controll();
        Map<String,String> map = new HashMap<String, String>();
        Map<String,Integer> map2 = new HashMap<String, Integer>();
        // Write a message to the database
        map.put("username",usname);
        map.put("password",pass);
        map.put("name",name);
        map.put("dob",dob);
        map.put("comment",cmt);
        map.put("trophy",String.valueOf(trophy));
        map.put("ID",String.valueOf(ID));
        data_controll.pushStringData(map,ID);
    }
    public void getNewIDData(Context context)
    {
        Data_Controll data_controll = new Data_Controll();
        ArrayList<User_Firebase>  arr = new ArrayList<>();
        data_controll.getData(arr,context);
//        Toast.makeText(context," "+arr.get(0)+" "+arr.get(1),Toast.LENGTH_LONG);
    }
    /*
    cập nhật adpter theo realtime
     */
    public void updateADTData(Context context, ArchivementAdapter adapter)
    {
        Data_Controll data_controll = new Data_Controll();
        ArrayList<User_Firebase>  arr = new ArrayList<>();
        data_controll.getADTData(arr,context,adapter);
//        Toast.makeText(context," "+arr.get(0)+" "+arr.get(1),Toast.LENGTH_LONG);
    }
}
