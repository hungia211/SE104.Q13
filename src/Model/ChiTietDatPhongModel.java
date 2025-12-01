
package Model;

public class ChiTietDatPhongModel {
    private int MaHopDong;
    private int MaPhong;
    
    public ChiTietDatPhongModel(){
    }
    
    public ChiTietDatPhongModel(int MaHopDong, int MaPhong){
        this.MaHopDong = MaHopDong;
        this.MaPhong = MaPhong;
    }

    public int getMaHopDong() {
        return MaHopDong;
    }

    public void setMaHopDong(int MaHopDong) {
        this.MaHopDong = MaHopDong;
    }
    
    public int getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(int MaPhong) {
        this.MaPhong = MaPhong;
    }
    
}
