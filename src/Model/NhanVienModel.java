
package Model;

import java.time.LocalDate;


public class NhanVienModel {
    private int MaNV;
    private String TenNV;
    private String CCCD;
    private LocalDate NgaySinh;
    private String GioiTinh;
    private String DiaChi;
    private String SDT;
    private String LoaiNV; // 'Tạp vụ', 'Lễ tân', 'Quản lý'
    private String TaiKhoan;
    private String MatKhau;
    private long LuongCB;
    private String TrangThai;
    
    public NhanVienModel(){
    }

    public NhanVienModel(int MaNV, String TenNV, String CCCD, LocalDate NgaySinh, String GioiTinh, String DiaChi, String SDT, String LoaiNV, String TaiKhoan, String MatKhau, long LuongCB, String TrangThai) {
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.CCCD = CCCD;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.LoaiNV = LoaiNV;
        this.TaiKhoan = TaiKhoan;
        this.MatKhau = MatKhau;
        this.LuongCB = LuongCB;
        this.TrangThai = TrangThai;
    }

    public NhanVienModel(String TenNV, String CCCD, LocalDate NgaySinh, String GioiTinh, String DiaChi, String SDT, String LoaiNV) {
        this.TenNV = TenNV;
        this.CCCD = CCCD;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.LoaiNV = LoaiNV;
    }

    
    
    public NhanVienModel(String TenNV, String CCCD, LocalDate NgaySinh, String GioiTinh, String DiaChi, String SDT, String LoaiNV, String TaiKhoan, String MatKhau, long LuongCB) {
        this.TenNV = TenNV;
        this.CCCD = CCCD;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.LoaiNV = LoaiNV;
        this.TaiKhoan = TaiKhoan;
        this.MatKhau = MatKhau;
        this.LuongCB = LuongCB;
    }

    
    
    public NhanVienModel(int MaNV, String TenNV, String CCCD, LocalDate NgaySinh, String GioiTinh, String DiaChi, String SDT, String LoaiNV, String TaiKhoan, String MatKhau, long LuongCB) {
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.CCCD = CCCD;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.LoaiNV = LoaiNV;
        this.TaiKhoan = TaiKhoan;
        this.MatKhau = MatKhau;
        this.LuongCB = LuongCB;
    }
    

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
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

    public String getLoaiNV() {
        return LoaiNV;
    }

    public void setLoaiNV(String LoaiNV) {
        this.LoaiNV = LoaiNV;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String TaiKhoan) {
        this.TaiKhoan = TaiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }
    
    public long getLuongCB() {
        return LuongCB;
    }

    public void setLuongCB(long LuongCB) {
        this.LuongCB = LuongCB;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
    
    
}
