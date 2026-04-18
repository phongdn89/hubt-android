package com.hubt.less10;

public class Fruit {
    private String tentraicay;
    private String mieuta;
    private double gia;
    private int anhid;

    public Fruit(String tentraicay, String mieuta, double gia, int anhid) {
        this.tentraicay = tentraicay;
        this.mieuta = mieuta;
        this.gia = gia;
        this.anhid = anhid;
    }

    public String getTentraicay() {
        return tentraicay;
    }

    public void setTentraicay(String tentraicay) {
        this.tentraicay = tentraicay;
    }

    public String getMieuta() {
        return mieuta;
    }

    public void setMieuta(String mieuta) {
        this.mieuta = mieuta;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getAnhid() {
        return anhid;
    }

    public void setAnhid(int anhid) {
        this.anhid = anhid;
    }
}
