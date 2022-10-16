package com.example.math_dices.model;

public class Archivement {
    private int uID;
    private int trophy;
    private String cmt;

    public Archivement(int uID,int trophy, String cmt) {
        this.uID = uID;
        this.trophy = trophy;

        this.cmt = cmt;
    }

    public Archivement() {

    }

    public int getuID() {
        return uID;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public int getTrophy() {
        return trophy;
    }

    public void setTrophy(int trophy) {
        this.trophy = trophy;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }
}
