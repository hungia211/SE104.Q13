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

    public static ArrayList<ThongKeModel> getDoanhThuTheoNam(int nam) {
        ArrayList<ThongKeModel> ds = new ArrayList<>();
        String sql = "SELECT TK.Thang, TK.MaLoai, LP.TenLoai, SUM(TK.DoanhThu) AS DoanhThu "
                + "FROM THONGKE TK "
                + "JOIN LOAIPHONG LP ON TK.MaLoai = LP.MaLoai "
                + "WHERE TK.Nam = ? "
                + "GROUP BY TK.Thang, TK.MaLoai, LP.TenLoai "
                + "ORDER BY TK.Thang, TK.MaLoai";
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nam);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ThongKeModel tk = new ThongKeModel();
                    tk.setThang(rs.getInt("Thang"));
                    tk.setMaLoai(rs.getInt("MaLoai"));
                    tk.setTenLoai(rs.getString("TenLoai"));
                    tk.setDoanhThu(rs.getDouble("DoanhThu"));
                    tk.setNam(nam);
                    ds.add(tk);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ds;
    }

    public static void capNhatThongKeTheoHoaDon(int maHopDong) {
        String sql = "SELECT EXTRACT(MONTH FROM NgayLapHD) AS Thang, EXTRACT(YEAR FROM NgayLapHD) AS Nam "
                + "FROM HOADON WHERE MaHD = (SELECT MAX(MaHD) FROM HOADON WHERE MaHopDong = ?)";
        try (Connection con = JDBCUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maHopDong);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int thang = rs.getInt("Thang");
                    int nam = rs.getInt("Nam");
                    capNhatThongKeTheoThangNam(thang, nam);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void capNhatThongKeTheoThangNam(int thang, int nam) {
        String sql = "MERGE INTO THONGKE t "
                + "USING ("
                + "  SELECT EXTRACT(MONTH FROM h.NgayLapHD) AS Thang, "
                + "         EXTRACT(YEAR FROM h.NgayLapHD) AS Nam, "
                + "         p.MaLoai, "
                + "         SUM(CASE WHEN tg.TongGia = 0 THEN 0 "
                + "             ELSE h.TongTien * (lp.Gia / tg.TongGia) END) AS DoanhThu "
                + "  FROM HOADON h "
                + "  JOIN HOPDONG hd ON h.MaHopDong = hd.MaHopDong "
                + "  JOIN CHITIETDATPHONG c ON hd.MaHopDong = c.MaHopDong "
                + "  JOIN PHONG p ON c.MaPhong = p.MaPhong "
                + "  JOIN LOAIPHONG lp ON p.MaLoai = lp.MaLoai "
                + "  JOIN ("
                + "    SELECT hd2.MaHopDong, SUM(lp2.Gia) AS TongGia "
                + "    FROM HOPDONG hd2 "
                + "    JOIN CHITIETDATPHONG c2 ON hd2.MaHopDong = c2.MaHopDong "
                + "    JOIN PHONG p2 ON c2.MaPhong = p2.MaPhong "
                + "    JOIN LOAIPHONG lp2 ON p2.MaLoai = lp2.MaLoai "
                + "    GROUP BY hd2.MaHopDong "
                + "  ) tg ON tg.MaHopDong = h.MaHopDong "
                + "  WHERE EXTRACT(MONTH FROM h.NgayLapHD) = ? "
                + "    AND EXTRACT(YEAR FROM h.NgayLapHD) = ? "
                + "  GROUP BY EXTRACT(MONTH FROM h.NgayLapHD), EXTRACT(YEAR FROM h.NgayLapHD), p.MaLoai "
                + ") src "
                + "ON (t.Thang = src.Thang AND t.Nam = src.Nam AND t.MaLoai = src.MaLoai) "
                + "WHEN MATCHED THEN UPDATE SET t.DoanhThu = src.DoanhThu "
                + "WHEN NOT MATCHED THEN "
                + "  INSERT (MaTK, Thang, Nam, MaLoai, DoanhThu) "
                + "  VALUES (ThongKe_Seq.NEXTVAL, src.Thang, src.Nam, src.MaLoai, src.DoanhThu)";

        try (Connection con = JDBCUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, thang);
            ps.setInt(2, nam);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
