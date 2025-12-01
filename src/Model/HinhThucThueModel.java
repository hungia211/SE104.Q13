
package Model;


public class HinhThucThueModel {
    private int MaHT;
    private String TenHT;

    public HinhThucThueModel(){
        
    }
    
    public HinhThucThueModel(int MaHT, String TenHT){
        this.MaHT = MaHT;
        this.TenHT = TenHT;
    }
    
    public int getMaHT() {
        return MaHT;
    }

    public void setMaHT(int MaHT) {
        this.MaHT = MaHT;
    }

    public String getTenHT() {
        return TenHT;
    }

    public void setTenHT(String TenHT) {
        this.TenHT = TenHT;
    }
    
}
