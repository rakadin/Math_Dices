package com.example.math_dices.model;

public class TotalArchivement {
    public int ID;
    public String name;
    public String cmt;
    public int trophy;

    public TotalArchivement(int ID, String name, String cmt, int trophy) {
        this.ID = ID;
        this.name = name;
        this.cmt = cmt;
        this.trophy = trophy;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public int getTrophy() {
        return trophy;
    }

    public void setTrophy(int trophy) {
        this.trophy = trophy;
    }
}
