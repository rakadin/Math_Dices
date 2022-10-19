package com.example.math_dices.firebase;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.math_dices.adapter.ArchivementAdapter;
import com.example.math_dices.model.Archivement;
import com.example.math_dices.model.Users;
import com.example.math_dices.sqlite.ArchivementDAO;
import com.example.math_dices.sqlite.UserDAO;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Data_Controll {
    private UserDAO userDAO;
    ArchivementDAO archivementDAO;
    final FirebaseDatabase database = FirebaseDatabase.getInstance( "https://mathdice-5f3f6-default-rtdb.asia-southeast1.firebasedatabase.app");
    public void pushStringData(Map<String,String> map,int id)// đẩy data lên firebase
    {
        DatabaseReference myRef2 = database.getReference("User/"+id);
        myRef2.setValue(map);
        map.clear();

    }
    // cập nhật dữ liệu theo string nào đó
    public void updateStringData(Map<String,Object> map,int id)
    {
        DatabaseReference myRef2 = database.getReference("User/"+id);
        myRef2.updateChildren(map);
        map.clear();
    }
    /*
    nếu có data mới được push online thì luôn cập nhật
     */
    public void getData(ArrayList<User_Firebase> arr, Context context) // lấy dữ liệu về
    {
        userDAO = new UserDAO(context);
        archivementDAO = new ArchivementDAO(context);
//        ArrayList<String> arr = new ArrayList<String>();
        DatabaseReference myRef = database.getReference("User");
        // Read from the database
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) { // khi child được thêm mới
                User_Firebase user = dataSnapshot.getValue(User_Firebase.class);
                if(user != null)
                {   // lấy dữ liệu về theo thời gian thực
                    arr.add(user);
                    int k = arr.size();
                    Users users = new Users();
                    Archivement ar = new Archivement();
                    int i = k-1;
                        users.setId(Integer.valueOf(arr.get(i).getID()));
                        users.setUsername(arr.get(i).getUsername());
                        users.setPassword(arr.get(i).getPassword());
                        users.setName(arr.get(i).getName());
                        users.setDob(arr.get(i).getDob());
                        ar.setuID(Integer.valueOf(arr.get(i).getID()));
                        ar.setCmt(arr.get(i).getComment());
                        ar.setTrophy(Integer.valueOf(arr.get(i).getTrophy()));
                        // gửi dữ liệu lấy được vào SQLITE
                        userDAO.insertUserFromFB(users);
                        archivementDAO.insertArchiveFromFB(ar,Integer.valueOf(arr.get(i).getID()));
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) { // khi dữ liệu online bị thay đổi
                int id = Integer.valueOf(dataSnapshot.child("ID").getValue().toString());
                String comment = dataSnapshot.child("comment").getValue().toString();
                String name = dataSnapshot.child("name").getValue().toString();
                String trophy = dataSnapshot.child("trophy").getValue().toString();
                String dob = dataSnapshot.child("dob").getValue().toString();
                String password = dataSnapshot.child("password").getValue().toString();
                userDAO.setDobByID(dob,id);
                userDAO.setNewPassByID(password,id);
                userDAO.setNameByID(name,id);
                archivementDAO.setcmtByID(trophy,id);
                archivementDAO.setcmtByID(comment,id);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
    /*
 nếu có data mới được push online thì luôn cập nhật adpter
  */
    public void getADTData(ArrayList<User_Firebase> arr, Context context, ArchivementAdapter adapter) // lấy dữ liệu về
    {
        userDAO = new UserDAO(context);
        archivementDAO = new ArchivementDAO(context);
//        ArrayList<String> arr = new ArrayList<String>();
        DatabaseReference myRef = database.getReference("User");
        // Read from the database
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) { // khi child được thêm mới
                User_Firebase user = dataSnapshot.getValue(User_Firebase.class);
                if(user != null)
                {   // lấy dữ liệu về theo thời gian thực
                    arr.add(user);
                    int k = arr.size();
                    Users users = new Users();
                    Archivement ar = new Archivement();
                    int i = k-1;
                    users.setId(Integer.valueOf(arr.get(i).getID()));
                    users.setUsername(arr.get(i).getUsername());
                    users.setPassword(arr.get(i).getPassword());
                    users.setName(arr.get(i).getName());
                    users.setDob(arr.get(i).getDob());
                    ar.setuID(Integer.valueOf(arr.get(i).getID()));
                    ar.setCmt(arr.get(i).getComment());
                    ar.setTrophy(Integer.valueOf(arr.get(i).getTrophy()));
                    // gửi dữ liệu lấy được vào SQLITE
                    userDAO.insertUserFromFB(users);
                    archivementDAO.insertArchiveFromFB(ar,Integer.valueOf(arr.get(i).getID()));
                    adapter.notifyDataSetChanged(); // dữ liệu của adpter thay đổi
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
}
