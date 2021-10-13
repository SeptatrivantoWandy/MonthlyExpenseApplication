package com.example.a2301869512_septatrivantowandy_uts_mcs;

public class MonEx {
    private String monexId;
    private String monexName;
    private int monexPrice;
    private String monexDate;

    public MonEx(String monexId, String monexName, int monexPrice, String monexDate) {
        this.monexId = monexId;
        this.monexName = monexName;
        this.monexPrice = monexPrice;
        this.monexDate = monexDate;
    }

    public String getMonexId() {
        return monexId;
    }

    public void setMonexId(String monexId) {
        this.monexId = monexId;
    }

    public String getMonexName() {
        return monexName;
    }

    public void setMonexName(String monexName) {
        this.monexName = monexName;
    }

    public int getMonexPrice() {
        return monexPrice;
    }

    public void setMonexPrice(int monexPrice) {
        this.monexPrice = monexPrice;
    }

    public String getMonexDate() {
        return monexDate;
    }

    public void setMonexDate(String monexDate) {
        this.monexDate = monexDate;
    }
}
