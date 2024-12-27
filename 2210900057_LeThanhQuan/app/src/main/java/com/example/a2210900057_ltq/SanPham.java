package com.example.a2210900057_ltq;

import java.io.Serializable;

public class SanPham implements Serializable {

    private int maSP;
    private String tenSP;
    private int soLuong;
    private float donGia;

    public SanPham(int maSP, String tenSP, int soLuong, float donGia) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public float getThanhTien() {
        float thanhTien = this.soLuong * this.donGia;
        if (this.soLuong > 10) {
            thanhTien -= thanhTien * 0.1f; // Giảm 10% nếu số lượng > 10
        }
        return thanhTien;
    }
}
