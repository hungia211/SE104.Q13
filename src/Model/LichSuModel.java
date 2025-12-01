/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDateTime;

/**
 *
 * @author phuch
 */
public class LichSuModel {
    private int MaKH;
    private int MaHD;
    private String tenKH;
    private LocalDateTime tGlaphopdong;
    private LocalDateTime tGNhanPhong;
    private LocalDateTime tGTraPhong;
    private String tinhtrangHD;
    private double TongTien;

    public LichSuModel() {
    }

    public LichSuModel(int MaKH, int MaHD, String tenKH, LocalDateTime tGlaphopdong, LocalDateTime tGNhanPhong, LocalDateTime tGTraPhong, String tinhtrangHD, double TongTien) {
        this.MaKH = MaKH;
        this.MaHD = MaHD;
        this.tenKH = tenKH;
        this.tGlaphopdong = tGlaphopdong;
        this.tGNhanPhong = tGNhanPhong;
        this.tGTraPhong = tGTraPhong;
        this.tinhtrangHD = tinhtrangHD;
        this.TongTien = TongTien;
    }

    public String getTinhtrangHD() {
        return tinhtrangHD;
    }

    public void setTinhtrangHD(String tinhtrangHD) {
        this.tinhtrangHD = tinhtrangHD;
    }
    public LocalDateTime gettGlaphopdong() {
        return tGlaphopdong;
    }

    public void settGlaphopdong(LocalDateTime tGlaphopdong) {
        this.tGlaphopdong = tGlaphopdong;
    }
    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }
    public int getMaKH() {
        return MaKH;
    }
    public void setMaKH(int MaKH) {
        this.MaKH = MaKH;
    }

    

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public LocalDateTime gettGNhanPhong() {
        return tGNhanPhong;
    }

    public void settGNhanPhong(LocalDateTime tGNhanPhong) {
        this.tGNhanPhong = tGNhanPhong;
    }

    public LocalDateTime gettGTraPhong() {
        return tGTraPhong;
    }

    public void settGTraPhong(LocalDateTime tGTraPhong) {
        this.tGTraPhong = tGTraPhong;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien (double TongTien) {
        this.TongTien = TongTien;
    }
    
}
