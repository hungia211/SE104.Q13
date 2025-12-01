
package Model;


public class LoaiPhongModel {
    private int MaLoai;
    private String Tenloai;
    private long Gia;  

    public LoaiPhongModel() {
    }

    public LoaiPhongModel(int MaLoai, String Tenloai, long Gia) {
        this.MaLoai = MaLoai;
        this.Tenloai = Tenloai;
        this.Gia = Gia;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public String getTenloai() {
        return Tenloai;
    }

    public long getGia() {
        return Gia;
    }

    public void setMaLoai(int MaLoai) {
        this.MaLoai = MaLoai;
    }

    public void setTenloai(String Tenloai) {
        this.Tenloai = Tenloai;
    }

    public void setGia(long Gia) {
        this.Gia = Gia;
    }
    
    
    
}
