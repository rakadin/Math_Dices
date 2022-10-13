package com.example.math_dices.model;

public class Archivement {
    private int trophy;
    private String cmt;

    public Archivement(int trophy, String cmt) {
        this.trophy = trophy;
        this.cmt = cmt;
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
