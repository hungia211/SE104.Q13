
package Model;

import java.time.LocalDateTime;


public class HopDongModel {
    private int MaHopDong;
    private int MaKH;  
    private LocalDateTime NgayLapHopDong;
    private LocalDateTime TGNhanPhong;
    private LocalDateTime TGTraPhong;
    private String TinhTrangHD;
    private double TriGiaHopDong; 
    private String HinhThucThue;
    
    public HopDongModel(){
    
    }

    public HopDongModel(int MaHopDong, int MaKH, LocalDateTime NgayLapHopDong, LocalDateTime TGNhanPhong, LocalDateTime TGTraPhong, String TinhTrangHD, double TriGiaHopDong) {
        this.MaHopDong = MaHopDong;
        this.MaKH = MaKH;
        this.NgayLapHopDong = NgayLapHopDong;
        this.TGNhanPhong = TGNhanPhong;
        this.TGTraPhong = TGTraPhong;
        this.TinhTrangHD = TinhTrangHD;
        this.TriGiaHopDong = TriGiaHopDong;
    }
    
    

    public HopDongModel(int MaKH, LocalDateTime NgayLapHopDong, LocalDateTime TGNhanPhong, LocalDateTime TGTraPhong, String TinhTrangHD, double TriGiaHopDong) {
        this.MaKH = MaKH;
        this.NgayLapHopDong = NgayLapHopDong;
        this.TGNhanPhong = TGNhanPhong;
        this.TGTraPhong = TGTraPhong;
        this.TinhTrangHD = TinhTrangHD;
        this.TriGiaHopDong = TriGiaHopDong;
    }
    
    

    public HopDongModel(int MaKH, LocalDateTime NgayLapHopDong, LocalDateTime TGNhanPhong, LocalDateTime TGTraPhong, String TinhTrangHD, double TriGiaHopDong, String HinhThucThue) {
        this.MaKH = MaKH;
        this.NgayLapHopDong = NgayLapHopDong;
        this.TGNhanPhong = TGNhanPhong;
        this.TGTraPhong = TGTraPhong;
        this.TinhTrangHD = TinhTrangHD;
        this.TriGiaHopDong = TriGiaHopDong;
        this.HinhThucThue = HinhThucThue;
    }
    
    
    public HopDongModel(int MaHopDong, int MaKH, LocalDateTime NgayLapHopDong, LocalDateTime TGNhanPhong, LocalDateTime TGTraPhong, String TinhTrangHD, double TriGiaHopDong, String HinhThucThue){
        this.MaHopDong = MaHopDong;
        this.MaKH = MaKH;
        this.NgayLapHopDong = NgayLapHopDong;
        this.TGNhanPhong = TGNhanPhong;
        this.TGTraPhong = TGTraPhong;
        this.TinhTrangHD = TinhTrangHD;
        this.TriGiaHopDong = TriGiaHopDong;
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

    public String getTinhTrangHD() {
        return TinhTrangHD;
    }

    public void setTinhTrangHD(String TinhTrangHD) {
        this.TinhTrangHD = TinhTrangHD;
    }

    public double getTriGiaHopDong() {
        return TriGiaHopDong;
    }

    public void setTriGiaHopDong(double TriGiaHopDong) {
        this.TriGiaHopDong = TriGiaHopDong;
    }

    public String getHinhThucThue() {
        return HinhThucThue;
    }

    public void setHinhThucThue(String HinhThucThue) {
        this.HinhThucThue = HinhThucThue;
    }
    
}
