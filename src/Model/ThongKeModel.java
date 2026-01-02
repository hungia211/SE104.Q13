package Model;

public class ThongKeModel {
    private int MaTK;
    private int Thang;
    private int Nam;
    private int MaLoai;
    private double DoanhThu;
    private String TenLoai;

    public ThongKeModel() {
    }

    public ThongKeModel(int MaTK, int Thang, int Nam, int MaLoai, double DoanhThu) {
        this.MaTK = MaTK;
        this.Thang = Thang;
        this.Nam = Nam;
        this.MaLoai = MaLoai;
        this.DoanhThu = DoanhThu;
    }

    public ThongKeModel(int MaTK, int Thang, int Nam, int MaLoai, String TenLoai, double DoanhThu) {
        this.MaTK = MaTK;
        this.Thang = Thang;
        this.Nam = Nam;
        this.MaLoai = MaLoai;
        this.TenLoai = TenLoai;
        this.DoanhThu = DoanhThu;
    }

    public int getMaTK() {
        return MaTK;
    }

    public void setMaTK(int MaTK) {
        this.MaTK = MaTK;
    }

    public int getThang() {
        return Thang;
    }

    public void setThang(int Thang) {
        this.Thang = Thang;
    }

    public int getNam() {
        return Nam;
    }

    public void setNam(int Nam) {
        this.Nam = Nam;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int MaLoai) {
        this.MaLoai = MaLoai;
    }

    public double getDoanhThu() {
        return DoanhThu;
    }

    public void setDoanhThu(double DoanhThu) {
        this.DoanhThu = DoanhThu;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String TenLoai) {
        this.TenLoai = TenLoai;
    }
}
