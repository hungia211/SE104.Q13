package Model;

import java.time.LocalDate;

public class HoaDonModel {

    private int MaHD;
    private int MaKM;
    private int MaHopDong;
    private LocalDate NgayLapHD;
    private double TongTien;
    private double TienHongTB;

    public HoaDonModel() {

    }

    public HoaDonModel(int MaHD, int MaKM, int MaHopDong, LocalDate NgayLapHD, double TongTien, double TienHongTB) {
        this.MaHD = MaHD;
        this.MaHopDong = MaHopDong;
        this.MaKM = MaKM;
        this.NgayLapHD = NgayLapHD;
        this.TongTien = TongTien;
        this.TienHongTB = TienHongTB;
    }

    public HoaDonModel(int MaHD, int MaKM, int MaHopDong, LocalDate NgayLapHD, double TongTien) {
        this.MaHD = MaHD;
        this.MaHopDong = MaHopDong;
        this.MaKM = MaKM;
        this.NgayLapHD = NgayLapHD;
        this.TongTien = TongTien;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public int getMaHopDong() {
        return MaHopDong;
    }

    public void setMaHopDong(int MaHopDong) {
        this.MaHopDong = MaHopDong;
    }

    public int getMaKM() {
        return MaKM;
    }

    public void setMaKM(int MaKM) {
        this.MaKM = MaKM;
    }

    public LocalDate getNgayLapHD() {
        return NgayLapHD;
    }

    public void setNgayLapHD(LocalDate NgayLapHD) {
        this.NgayLapHD = NgayLapHD;
    }

    public double getTongTien() {
        return TongTien;
    }

    public double getTienHongTB() {
        return TienHongTB;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }

    public void setTienHongTB(double TienHongTB) {
        this.TienHongTB = TienHongTB;
    }

}
