
package Model;

public class TrangBiModel {
    private int MaTB; 
    private String TenTB; 
    private double GiaTB;
    private int SoLuong;
    private int SoLuongHong;

    public TrangBiModel(){
        
    }
    
    public TrangBiModel(int MaTB, String TenTB, double GiaTB, int SoLuong, int SoLuongHong){
        this.MaTB = MaTB;
        this.TenTB = TenTB;
        this.GiaTB = GiaTB;
        this.SoLuong = SoLuong;
        this.SoLuongHong = SoLuongHong;
    }
    
    public int getMaTB() {
        return MaTB;
    }

    public void setMaTB(int MaTB) {
        this.MaTB = MaTB;
    }

    public String getTenTB() {
        return TenTB;
    }

    public void setTenTB(String TenTB) {
        this.TenTB = TenTB;
    }

    public double getGiaTB() {
        return GiaTB;
    }

    public void setGiaTB(double GiaTB) {
        this.GiaTB = GiaTB;
    }
    
    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public int getSoLuongHong() {
        return SoLuongHong;
    }

    public void setSoLuongHong(int SoLuongHong) {
        this.SoLuongHong = SoLuongHong;
    }
    
}
