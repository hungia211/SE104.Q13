
package Model;


public class PhongModel {
    private int MaPhong;
    private String MaLoai; 
    private String TinhTrang;
    
    public PhongModel(){
        
    }

    public PhongModel(int MaPhong, String MaLoai, String TinhTrang) {
        this.MaPhong = MaPhong;
        this.MaLoai = MaLoai;
        this.TinhTrang = TinhTrang;
    }
    
    public int getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(int MaPhong) {
        this.MaPhong = MaPhong;
    }

    public String getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(String MaLoai) {
        this.MaLoai = MaLoai;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String TinhTrang) {
        this.TinhTrang = TinhTrang;
    }
}
