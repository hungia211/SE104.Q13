
package Model;

import java.time.LocalDate;

public class KhachHangModel {
    private int MaKH;
    private String TenKH;
    private String CCCD;
    private LocalDate NgaySinh;
    private String GioiTinh;
    private String DiaChi;
    private String SDT;
    private int SoHopDong;
    
    public KhachHangModel(){
        
    }
    
    public KhachHangModel(int MaKH, String TenKH, String CCCD, LocalDate NgaySinh, String GioiTinh, String DiaChi, String SDT, int SoHopDong){
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.CCCD = CCCD;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.SoHopDong = SoHopDong;
    }
    
    public KhachHangModel(String TenKH, String CCCD, LocalDate NgaySinh, String GioiTinh, String DiaChi, String SDT, int SoHopDong){
        this.TenKH = TenKH;
        this.CCCD = CCCD;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.SoHopDong = SoHopDong;
    }
    
    public KhachHangModel(String TenKH, String CCCD, LocalDate NgaySinh, String GioiTinh, String DiaChi, String SDT){
        this.TenKH = TenKH;
        this.CCCD = CCCD;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int MaKH) {
        this.MaKH = MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public LocalDate getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(LocalDate NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public int getSoHopDong() {
        return SoHopDong;
    }

    public void setSoHopDong(int SoHopDong) {
        this.SoHopDong = SoHopDong;
    }

}
