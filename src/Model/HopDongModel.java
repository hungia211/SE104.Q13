package Model;

import java.time.LocalDateTime;

public class HopDongModel {
    private int MaHopDong;
    private int MaKH;
    private LocalDateTime NgayLapHopDong;
    private LocalDateTime TGNhanPhong;
    private LocalDateTime TGTraPhong;
    private int SoKhach;        // mới
    private String LoaiKH;      // mới
    private String HinhThucThue;

    public HopDongModel() {
    }

    // Constructor đầy đủ (có MaHopDong – dùng khi select từ DB)
    public HopDongModel(int MaHopDong, int MaKH,
                        LocalDateTime NgayLapHopDong,
                        LocalDateTime TGNhanPhong,
                        LocalDateTime TGTraPhong,
                        int SoKhach,
                        String LoaiKH,
                        String HinhThucThue) {
        this.MaHopDong = MaHopDong;
        this.MaKH = MaKH;
        this.NgayLapHopDong = NgayLapHopDong;
        this.TGNhanPhong = TGNhanPhong;
        this.TGTraPhong = TGTraPhong;
        this.SoKhach = SoKhach;
        this.LoaiKH = LoaiKH;
        this.HinhThucThue = HinhThucThue;
    }

    // Constructor không có MaHopDong (dùng khi insert)
    public HopDongModel(int MaKH,
                        LocalDateTime NgayLapHopDong,
                        LocalDateTime TGNhanPhong,
                        LocalDateTime TGTraPhong,
                        int SoKhach,
                        String LoaiKH,
                        String HinhThucThue) {
        this.MaKH = MaKH;
        this.NgayLapHopDong = NgayLapHopDong;
        this.TGNhanPhong = TGNhanPhong;
        this.TGTraPhong = TGTraPhong;
        this.SoKhach = SoKhach;
        this.LoaiKH = LoaiKH;
        this.HinhThucThue = HinhThucThue;
    }

    public int getMaHopDong() {
        return MaHopDong;
    }

    public void setMaHopDong(int MaHopDong) {
        this.MaHopDong = MaHopDong;
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int MaKH) {
        this.MaKH = MaKH;
    }

    public LocalDateTime getNgayLapHopDong() {
        return NgayLapHopDong;
    }

    public void setNgayLapHopDong(LocalDateTime NgayLapHopDong) {
        this.NgayLapHopDong = NgayLapHopDong;
    }

    public LocalDateTime getTGNhanPhong() {
        return TGNhanPhong;
    }

    public void setTGNhanPhong(LocalDateTime TGNhanPhong) {
        this.TGNhanPhong = TGNhanPhong;
    }

    public LocalDateTime getTGTraPhong() {
        return TGTraPhong;
    }

    public void setTGTraPhong(LocalDateTime TGTraPhong) {
        this.TGTraPhong = TGTraPhong;
    }

    public int getSoKhach() {
        return SoKhach;
    }

    public void setSoKhach(int SoKhach) {
        this.SoKhach = SoKhach;
    }

    public String getLoaiKH() {
        return LoaiKH;
    }

    public void setLoaiKH(String LoaiKH) {
        this.LoaiKH = LoaiKH;
    }

    public String getHinhThucThue() {
        return HinhThucThue;
    }

    public void setHinhThucThue(String HinhThucThue) {
        this.HinhThucThue = HinhThucThue;
    }
}
