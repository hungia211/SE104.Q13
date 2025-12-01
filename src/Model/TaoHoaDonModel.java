
package Model;

public class TaoHoaDonModel {
    private int MaHD;
    private int MaNV;
    
    public TaoHoaDonModel(){
        
    }
    
    public TaoHoaDonModel(int MaHD, int MaNV){
        this.MaHD = MaHD;
        this.MaNV = MaNV;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }
    
}
