package com.example.math_dices.firebase;

public class User_Firebase {
    String ID;
    String username;
    String password;
    String name;
    String dob;
    String trophy;
    String comment;

    public User_Firebase(String ID, String username, String password, String name, String dob, String trophy, String comment) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.name = name;
        this.dob = dob;
        this.trophy = trophy;
        this.comment = comment;
    }

    public User_Firebase() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getTrophy() {
        return trophy;
    }

    public void setTrophy(String trophy) {
        this.trophy = trophy;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
