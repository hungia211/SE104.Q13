
package Model;

import java.time.LocalDate;


public class KhuyenMaiModel {
    private int MaKM;
    private String TenKM; 
    private String MoTaKM; 
    private LocalDate NgayBatDau;
    private LocalDate NgayKetThuc;
    private double PhanTramKM; 
    
    public KhuyenMaiModel(){
        
    }
    
    public KhuyenMaiModel(int MaKM, String TenKM, String MoTaKM, LocalDate NgayBatDau, LocalDate NgayKetThuc, double PhanTramKM){
        this.MaKM = MaKM;
        this.TenKM = TenKM;
        this.MoTaKM = MoTaKM;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.PhanTramKM = PhanTramKM;
    }

    public KhuyenMaiModel(String TenKM, String MoTaKM, LocalDate NgayBatDau, LocalDate NgayKetThuc, double PhanTramKM){
        this.TenKM = TenKM;
        this.MoTaKM = MoTaKM;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.PhanTramKM = PhanTramKM;
    }

    
    public int getMaKM() {
        return MaKM;
    }

    public void setMaKM(int MaKM) {
        this.MaKM = MaKM;
    }

    public String getTenKM() {
        return TenKM;
    }

    public void setTenKM(String TenKM) {
        this.TenKM = TenKM;
    }

    public String getMoTaKM() {
        return MoTaKM;
    }

    public void setMoTaKM(String MoTaKM) {
        this.MoTaKM = MoTaKM;
    }

    public LocalDate getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(LocalDate NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(LocalDate NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }

    public double getPhanTramKM() {
        return PhanTramKM;
    }

    public void setPhanTramKM(double PhanTramKM) {
        this.PhanTramKM = PhanTramKM;
    }
    
}
