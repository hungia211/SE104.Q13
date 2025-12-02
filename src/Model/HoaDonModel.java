package Model;

import java.time.LocalDate;

public class HoaDonModel {

    private int MaHD;
    private int MaKM;
    private int MaHopDong;
    private LocalDate NgayLapHD;
    private double TongTien;
    private double TienHongTB;
    private int SoNgayThue;   // ⭐ MỚI 

    public HoaDonModel() {

    }

    // Constructor đầy đủ
    public HoaDonModel(int MaHD, int MaKM, int MaHopDong,
                       LocalDate NgayLapHD, double TongTien,
                       double TienHongTB, int SoNgayThue) {
        this.MaHD = MaHD;
        this.MaKM = MaKM;
        this.MaHopDong = MaHopDong;
        this.NgayLapHD = NgayLapHD;
        this.TongTien = TongTien;
        this.TienHongTB = TienHongTB;
        this.SoNgayThue = SoNgayThue;
    }

    // Constructor không có TienHongTB (optional)
    public HoaDonModel(int MaHD, int MaKM, int MaHopDong,
                       LocalDate NgayLapHD, double TongTien, int SoNgayThue) {
        this.MaHD = MaHD;
        this.MaKM = MaKM;
        this.MaHopDong = MaHopDong;
        this.NgayLapHD = NgayLapHD;
        this.TongTien = TongTien;
        this.SoNgayThue = SoNgayThue;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public int getMaKM() {
        return MaKM;
    }

    public void setMaKM(int MaKM) {
        this.MaKM = MaKM;
    }

    public int getMaHopDong() {
        return MaHopDong;
    }

    public void setMaHopDong(int MaHopDong) {
        this.MaHopDong = MaHopDong;
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

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }

    public double getTienHongTB() {
        return TienHongTB;
    }

    public void setTienHongTB(double TienHongTB) {
        this.TienHongTB = TienHongTB;
    }

    public int getSoNgayThue() {
        return SoNgayThue;
    }

    public void setSoNgayThue(int SoNgayThue) {
        this.SoNgayThue = SoNgayThue;
    }
}
