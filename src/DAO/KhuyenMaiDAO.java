package DAO;

import Connection.JDBCUtil;
import Model.KhachHangModel;
import Model.KhuyenMaiModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDate;

public class KhuyenMaiDAO {

    public static ArrayList<KhuyenMaiModel> getDSKhuyenMai() {
        ArrayList<KhuyenMaiModel> DSKM = new ArrayList<>();

        try {
            String sql = "SELECT * FROM KHUYENMAI ORDER BY MaKM ASC";
            Connection con = null;
            con = JDBCUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            DSKM.clear();
            while (rs.next()) {
                int MaKM = rs.getInt(1);
                String TenKM = rs.getString(2);
                String MoTaKM = rs.getString(3);
                LocalDate NgayBatDau = rs.getDate(4).toLocalDate();
                LocalDate NgayKetThuc = rs.getDate(5).toLocalDate();
                Double PhanTramKM = rs.getDouble(6);
                KhuyenMaiModel sp = new KhuyenMaiModel(MaKM, TenKM, MoTaKM, NgayBatDau, NgayKetThuc, PhanTramKM);
                DSKM.add(sp);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return DSKM;
    }

    public static boolean ThemKM(KhuyenMaiModel km) {
        try (Connection con = JDBCUtil.getConnection()) {
            String sql = "INSERT INTO KHUYENMAI(MaKM, TenKM, MoTaKM, NgayBatDau, NgayKetThuc, PhanTramKM) VALUES (KhuyenMai_Seq.NEXTVAL , ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql)) {
                ps.setString(1, km.getTenKM());
                ps.setString(2, km.getMoTaKM());
                ps.setObject(3, km.getNgayBatDau());
                ps.setObject(4, km.getNgayKetThuc());
                double phanTram = km.getPhanTramKM() / 100.0;
                ps.setDouble(5, phanTram);

                int affectedRows = ps.executeUpdate();
                System.out.println("Thêm thành công");
                return affectedRows > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static int XoaKM(int value) throws SQLException {
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(value);
        String sql = "DELETE FROM KHUYENMAI WHERE MaKM=?";
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        ps.setInt(1, value);
        return ps.executeUpdate();
    }

    public static boolean CapNhatKhuyenMai(int maKM, KhuyenMaiModel km) throws SQLException {
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        String sql = "UPDATE KHUYENMAI SET TENKM=?, MOTAKM=?, NGAYBATDAU=?, NGAYKETTHUC=?, PHANTRAMKM=? WHERE MAKM = ?";
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        ps.setString(1, km.getTenKM());
        ps.setString(2, km.getMoTaKM());
        ps.setObject(3, km.getNgayBatDau());
        ps.setObject(4, km.getNgayKetThuc());
        ps.setDouble(5, km.getPhanTramKM() / 100.0);
        ps.setInt(6, km.getMaKM());

        return ps.executeUpdate() > 0;
    }

    public static boolean isAvailable(String makm) throws SQLException {
        boolean result = false;
        String str = "";
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
        } catch (SQLException ex) {
        }
        String sql = "SELECT * FROM KHUYENMAI WHERE MaKM = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, makm);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            str = rs.getString("MaKM");
        }
        if (!str.equals("")) {
            result = true;
        }
        con.close();
        return result;
    }

    public static ArrayList<KhuyenMaiModel> TimKM(String luachon, String a) {
        ArrayList<KhuyenMaiModel> DSKM = new ArrayList<>();
        try {
            Connection con = null;
            con = JDBCUtil.getConnection();
            String sql = null;
            switch (luachon) {
                case "Mã KM":
                    sql = "SELECT * FROM KHUYENMAI WHERE LOWER(MAKM) LIKE LOWER(?)";
                    a = "%" + a + "%";
                    break;
                case "Tên KM":
                    sql = "SELECT * FROM KHUYENMAI WHERE LOWER(TENKM) LIKE LOWER(?)";
                    a = "%" + a + "%";
                    break;
                case "Ngày Bắt Đầu":
                    sql = "SELECT * FROM KHUYENMAI WHERE NgayBatDau= TO_DATE(?, 'DD-MM-YYYY')";
                    break;
                case "Ngày Kết Thúc":
                    sql = "SELECT * FROM KHUYENMAI WHERE NgayKetThuc= TO_DATE(?, 'DD-MM-YYYY')";
                    break;
                default:
                    throw new IllegalArgumentException("Lựa chọn tìm kiếm không hợp lệ: " + luachon);
            }

            if (sql == null) {
                throw new IllegalStateException("SQL query string cannot be null");
            }
            //PreparedStatement ps = con.prepareStatement(sql);
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, a);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhuyenMaiModel kms = new KhuyenMaiModel();
                kms.setMaKM(rs.getInt("MaKM"));
                kms.setTenKM(rs.getString("TenKM"));
                kms.setMoTaKM(rs.getString("MoTaKM"));
                kms.setNgayBatDau(rs.getDate("NgayBatDau").toLocalDate());
                kms.setNgayKetThuc(rs.getDate("NgayKetThuc").toLocalDate());
                kms.setPhanTramKM(rs.getDouble("PhanTramKM") * 100);
                DSKM.add(kms);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return DSKM;
    }

    public static KhuyenMaiModel getKMtheoMaKM(int maKM) throws SQLException {
        // Tạo câu truy vấn 
        String sql = "SELECT * FROM KHUYENMAI WHERE MaKM=?";

        // Tạo đối tượng connection
        Connection conn = null;
        KhuyenMaiModel km = null;

        try {
            conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, maKM);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String TenKM = rs.getString(2);
                String MoTaKM = rs.getString(3);
                LocalDate NgayBatDau = rs.getDate(4).toLocalDate();
                LocalDate NgayKetThuc = rs.getDate(5).toLocalDate();
                Double PhanTramKM = rs.getDouble(6);

                // gọi constructor
                km = new KhuyenMaiModel(maKM, TenKM, MoTaKM, NgayBatDau, NgayKetThuc, PhanTramKM);
            }
            rs.close();
            ps.close();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return km;
    }

    public static double getGiamGia_MaKM(int makm) {
        Double giam = 0.0;
        String sql = "SELECT PhanTramKM FROM KHUYENMAI WHERE MaKM = ?";
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, makm);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    giam = rs.getDouble("PhanTramKM");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return giam;
    }

    public static ArrayList<KhuyenMaiModel> getDSKhuyenMaitheoNgay(String ngayden, String ngaydi) {
        ArrayList<KhuyenMaiModel> DSKM = new ArrayList<>();

        try {
            String sql = "SELECT * FROM KHUYENMAI WHERE NGAYBATDAU <= TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS') AND NGAYKETTHUC >= TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS')";
            Connection con = null;
            con = JDBCUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ngaydi);
            ps.setString(2, ngayden);
            ResultSet rs = ps.executeQuery();
            

            DSKM.clear();
            while (rs.next()) {
                int MaKM = rs.getInt(1);
                String TenKM = rs.getString(2);
                String MoTaKM = rs.getString(3);
                LocalDate NgayBatDau = rs.getDate(4).toLocalDate();
                LocalDate NgayKetThuc = rs.getDate(5).toLocalDate();
                Double PhanTramKM = rs.getDouble(6);
                KhuyenMaiModel sp = new KhuyenMaiModel(MaKM, TenKM, MoTaKM, NgayBatDau, NgayKetThuc, PhanTramKM);
                DSKM.add(sp);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return DSKM;
    }
    
}
