
package Model;

public class HongTrangBiModel {
    private int MaTB;
    private int MaHD;
    private int SoLuongHong;
    
    public HongTrangBiModel(){
        
    }
    
    public HongTrangBiModel(int MaTB, int MaHD, int SoLuongHong){
        this.MaTB = MaTB;
        this.MaHD = MaHD;
        this.SoLuongHong = SoLuongHong;
    }

    public int getMaTB() {
        return MaTB;
    }

    public void setMaTB(int MaTB) {
        this.MaTB = MaTB;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public int getSoLuongHong() {
        return SoLuongHong;
    }

    public void setSoLuongHong(int SoLuongHong) {
        this.SoLuongHong = SoLuongHong;
    }
    
    
    
}
