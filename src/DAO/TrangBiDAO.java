package DAO;

import Connection.JDBCUtil;
import Model.TrangBiModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TrangBiDAO {

    static Connection con;
    static PreparedStatement ps;
    static ResultSet rs;

    public TrangBiDAO() {
        try {
            con = JDBCUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lấy tất cả dữ liệu từ bảng TrangBi
    public static ArrayList<TrangBiModel> getTrangBiData() {
        ArrayList<TrangBiModel> listTrangBi = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            ps = con.prepareStatement("select * from TrangBi ORDER BY MATB DESC");
            rs = ps.executeQuery();
            while (rs.next()) {
                TrangBiModel tb = new TrangBiModel();
                tb.setMaTB(rs.getInt("MaTB"));
                tb.setTenTB(rs.getString("TenTB"));
                tb.setGiaTB(rs.getDouble("GiaTB"));
                tb.setSoLuong(rs.getInt("SoLuong"));
                tb.setSoLuongHong(rs.getInt("SLHong"));
                listTrangBi.add(tb);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listTrangBi;
    }

    // Lấy dữ liệu dựa trên mã trang bị
    public static TrangBiModel getTrangBi_MaTB(int maTB) {
        TrangBiModel trangbi = new TrangBiModel();
        try {
            con = JDBCUtil.getConnection();
            ps = con.prepareStatement("select * from TrangBi where MaTB = ?");
            ps.setInt(1, maTB);
            rs = ps.executeQuery();
            while (rs.next()) {
                trangbi.setMaTB(rs.getInt("MaTB"));
                trangbi.setTenTB(rs.getString("TenTB"));
                trangbi.setGiaTB(rs.getDouble("GiaTB"));
                trangbi.setSoLuong(rs.getInt("SoLuong"));
                trangbi.setSoLuongHong(rs.getInt("SLHong"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return trangbi;
    }

    // Tìm trang bị theo phân loại 
    public static ArrayList<TrangBiModel> timTrangBi(String luachon, String thongtin) {
        ArrayList<TrangBiModel> listTrangBi = new ArrayList<>();
        try {
            con = JDBCUtil.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from TrangBi where " + luachon + " like ?");
            ps.setString(1, "%" + thongtin + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TrangBiModel tb = new TrangBiModel();
                tb.setMaTB(rs.getInt("MaTB"));
                tb.setTenTB(rs.getString("TenTB"));
                tb.setGiaTB(rs.getDouble("GiaTB"));
                tb.setSoLuong(rs.getInt("SoLuong"));
                tb.setSoLuongHong(rs.getInt("SLHong"));
                listTrangBi.add(tb);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listTrangBi;
    }

    // Xoá một trang bị
    public static boolean xoaTrangBi(int maTB) {
        try {
            con = JDBCUtil.getConnection();
            String sql = "DELETE FROM TrangBi WHERE MaTB = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, maTB);
            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Thêm một trang bị
    public static boolean themTrangBi(TrangBiModel tb) {
        String sql = "BEGIN "
                + "    Them_trangbi(?, ?, ?); "
                + "END;";
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tb.getTenTB());
            ps.setDouble(2, tb.getGiaTB());
            ps.setInt(3, tb.getSoLuong());
            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Sửa trang bị
    public static boolean suaTrangBi(TrangBiModel tb) {
        String sql = "{call update_trangbi(?, ?, ?, ?, ?)}";  // Gọi stored procedure
        try (Connection con = JDBCUtil.getConnection(); var cs = con.prepareCall(sql)) {

            cs.setString(1, tb.getTenTB());
            cs.setDouble(2, tb.getGiaTB());
            cs.setInt(3, tb.getSoLuong());
            cs.setInt(4, tb.getSoLuongHong());
            cs.setInt(5, tb.getMaTB());

            // Ghi thời gian bắt đầu
            long startTime = System.currentTimeMillis();

            // Thực hiện stored procedure
            boolean result = cs.executeUpdate() > 0;

            // Ghi thời gian kết thúc
            long endTime = System.currentTimeMillis();

            // Tính thời gian chạy
            long duration = endTime - startTime;

            System.out.println("Stored procedure executed in " + duration + " milliseconds.");

            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error executing SQL: " + ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static int getMaxRow() {
        int row = 0;
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT MAX(MaTB) FROM TRANGBI")) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    row = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return row;
    }

    // Tìm trang bị dựa trên tên trang bị
    public static TrangBiModel getTrangBi_TenTB(String tenTB) {
        TrangBiModel trangbi = new TrangBiModel();
        try {
            con = JDBCUtil.getConnection();
            ps = con.prepareStatement("select * from TrangBi where TenTB = ?");
            ps.setString(1, tenTB);
            rs = ps.executeQuery();
            while (rs.next()) {
                trangbi.setMaTB(rs.getInt("MaTB"));
                trangbi.setTenTB(rs.getString("TenTB"));
                trangbi.setGiaTB(rs.getDouble("GiaTB"));
                trangbi.setSoLuong(rs.getInt("SoLuong"));
                trangbi.setSoLuongHong(rs.getInt("SLHong"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return trangbi;
    }

    // Thêm vào bảng HONGTRANGBI
    public static void themHONGTRANGBI(ArrayList<Vector> tbh, int maHD) {
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement ps = con.prepareStatement("INSERT INTO HONGTRANGBI VALUES (?, ?)")) {
            for (Vector trangBi : tbh) {
                int maTB = (int) trangBi.get(0); // Lấy mã trang bị từ Vector
                ps.setInt(1, maTB);
                ps.setInt(2, maHD);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Cập nhật số lượng hỏng nếu có
    public static void suaTBHong(ArrayList<Vector> tbh) {
        try (Connection con = JDBCUtil.getConnection()) {
            for (Vector tb : tbh) {
                int maTB = (int) tb.get(0);
                int soLuongHong = (int) tb.get(1);

                // Tìm trang bị dựa trên mã trang bị
                TrangBiModel trangbi = getTrangBi_MaTB(maTB);

                // Nếu tìm thấy trang bị, cập nhật số lượng hỏng
                if (trangbi != null) {
                    trangbi.setSoLuongHong(soLuongHong);
                    trangbi.setSoLuong(trangbi.getSoLuong() - (int) tb.get(1));
                    // Cập nhật vào cơ sở dữ liệu
                    suaTrangBi(trangbi);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
