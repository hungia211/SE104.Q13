/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author trung
 */

public class ThongKeModel {

    private int maTK;
    private int thang;
    private int nam;
    private int maLoai;
    private double doanhThu;

    public ThongKeModel() {
    }

    public ThongKeModel(int maTK, int thang, int nam, int maLoai, double doanhThu) {
        this.maTK = maTK;
        this.thang = thang;
        this.nam = nam;
        this.maLoai = maLoai;
        this.doanhThu = doanhThu;
    }

    public int getMaTK() {
        return maTK;
    }

    public void setMaTK(int maTK) {
        this.maTK = maTK;
    }

    public int getThang() {
        return thang;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public double getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(double doanhThu) {
        this.doanhThu = doanhThu;
    }

    @Override
    public String toString() {
        return "ThongKeModel{" +
                "maTK=" + maTK +
                ", thang=" + thang +
                ", nam=" + nam +
                ", maLoai=" + maLoai +
                ", doanhThu=" + doanhThu +
                '}';
    }
}

