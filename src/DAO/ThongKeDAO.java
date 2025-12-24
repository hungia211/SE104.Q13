package DAO;

import Connection.JDBCUtil;
import Model.ThongKeModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ThongKeDAO {

    public static ArrayList<ThongKeModel> getThongKeByThangNam(int thang, int nam) {
        ArrayList<ThongKeModel> ds = new ArrayList<>();
        String sql = "SELECT TK.MaTK, TK.Thang, TK.Nam, TK.MaLoai, LP.TenLoai, TK.DoanhThu "
                + "FROM THONGKE TK "
                + "JOIN LOAIPHONG LP ON TK.MaLoai = LP.MaLoai "
                + "WHERE TK.Thang = ? AND TK.Nam = ? "
                + "ORDER BY TK.MaLoai";
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, thang);
            ps.setInt(2, nam);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ThongKeModel tk = new ThongKeModel(
                            rs.getInt("MaTK"),
                            rs.getInt("Thang"),
                            rs.getInt("Nam"),
                            rs.getInt("MaLoai"),
                            rs.getString("TenLoai"),
                            rs.getDouble("DoanhThu")
                    );
                    ds.add(tk);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ds;
    }

    public static double getTongDoanhThu(int thang, int nam) {
        String sql = "SELECT SUM(DoanhThu) AS TongDoanhThu FROM THONGKE WHERE Thang = ? AND Nam = ?";
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, thang);
            ps.setInt(2, nam);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("TongDoanhThu");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static ArrayList<Integer> getNamThongKe() {
        ArrayList<Integer> ds = new ArrayList<>();
        String sql = "SELECT DISTINCT Nam FROM THONGKE ORDER BY Nam DESC";
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ds.add(rs.getInt("Nam"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ds;
    }

    public static ArrayList<Integer> getThangThongKe(int nam) {
        ArrayList<Integer> ds = new ArrayList<>();
        String sql = "SELECT DISTINCT Thang FROM THONGKE WHERE Nam = ? ORDER BY Thang";
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nam);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ds.add(rs.getInt("Thang"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ds;
    }

    public static boolean themThongKe(ThongKeModel tk) {
        String sql = "INSERT INTO THONGKE (MaTK, Thang, Nam, MaLoai, DoanhThu) "
                + "VALUES (ThongKe_Seq.NEXTVAL, ?, ?, ?, ?)";
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, tk.getThang());
            ps.setInt(2, tk.getNam());
            ps.setInt(3, tk.getMaLoai());
            ps.setDouble(4, tk.getDoanhThu());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
