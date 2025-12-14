package Model;

public class ChiTietDatPhongModel {
    private int MaHopDong;
    private int MaPhong;
    private int SoNguoi;   // ➜ Thêm số khách

    public ChiTietDatPhongModel() {
    }

    // Constructor 2 tham số
    public ChiTietDatPhongModel(int MaHopDong, int MaPhong) {
        this.MaHopDong = MaHopDong;
        this.MaPhong = MaPhong;
    }

    // Constructor có số người
    public ChiTietDatPhongModel(int MaHopDong, int MaPhong, int SoNguoi) {
        this.MaHopDong = MaHopDong;
        this.MaPhong = MaPhong;
        this.SoNguoi = SoNguoi;
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

    public int getSoNguoi() {
        return SoNguoi;
    }

    public void setSoNguoi(int SoNguoi) {
        this.SoNguoi = SoNguoi;
    }
}
