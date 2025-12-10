
package Model;


public class PhongModel {
    private int MaPhong;
//    private String LoaiPhong; 
//    private String KieuPhong;
//    private long GiaPhong;
    private String TinhTrang;
    private LoaiPhongModel LoaiPhong;   // chứa cả object

    public PhongModel(int MaPhong, LoaiPhongModel LoaiPhong, String TinhTrang) {
        this.MaPhong = MaPhong;
        this.LoaiPhong = LoaiPhong;
        this.TinhTrang = TinhTrang;
    }
    
    public PhongModel(){
        
    }

    public LoaiPhongModel getLoaiPhong() {
        return LoaiPhong;
    }

    public void setLoaiPhong(LoaiPhongModel LoaiPhong) {
        this.LoaiPhong = LoaiPhong;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String TinhTrang) {
        this.TinhTrang = TinhTrang;
    }
    
    
    public int getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(int MaPhong) {
        this.MaPhong = MaPhong;
    }
  
   
}
