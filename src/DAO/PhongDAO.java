package DAO;

import Connection.JDBCUtil;
import Model.PhongModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PhongDAO {

    // lấy danh sách phòng có trong cơ sở dữ liệu
    public static ArrayList<PhongModel> hungia_getDSPhong() {
        ArrayList<PhongModel> DS_Phong = new ArrayList<>();
        String sql = "SELECT * FROM PHONG ORDER BY MAPHONG ASC";
        try {
            Connection con = null;
            con = JDBCUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DS_Phong.add(new PhongModel(
                        rs.getInt("MaPhong"),
                        rs.getString("LoaiPhong"),
                        rs.getString("KieuPhong"),
                        rs.getLong("Gia")
                ));

            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DS_Phong;
    }

    public static ArrayList<PhongModel> hungia_getDStheoNgay(String ngayNhan, String ngayTra) {
        ArrayList<PhongModel> DS_Phong = new ArrayList<>();
        String sql = "SELECT * FROM PHONG WHERE MAPHONG NOT IN (SELECT p.MAPHONG FROM PHONG p "
                + "INNER JOIN CHITIETDATPHONG ct ON ct.MAPHONG = p.MAPHONG "
                + "INNER JOIN HOPDONG h ON h.MAHOPDONG = ct.MAHOPDONG "
                + "WHERE (H.TGNHANPHONG < TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS') AND H.TGTRAPHONG >= TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS')) " // Thêm điều kiện này để bao gồm cả ngày 15
                + "OR (H.TGNHANPHONG = TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS') AND H.TGTRAPHONG > TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS')))"; // Thêm điều kiện này để bao gồm cả ngày 15

        try {
            Connection con = JDBCUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ngayTra);
            ps.setString(2, ngayNhan);
            ps.setString(3, ngayTra);
            ps.setString(4, ngayNhan);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DS_Phong.add(new PhongModel(
                        rs.getInt("MaPhong"),
                        rs.getString("LoaiPhong"),
                        rs.getString("KieuPhong"),
                        rs.getLong("Gia")
                ));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DS_Phong;
    }

    public static ArrayList<PhongModel> hungia_getDStheoLoaiPhong(String loaiphong, String ngayNhan, String ngayTra) {
        ArrayList<PhongModel> DS_Phong = new ArrayList<>();
        String sql = "SELECT * FROM PHONG WHERE LOAIPHONG = ? AND MAPHONG NOT IN (SELECT p.MAPHONG FROM PHONG p "
                + "INNER JOIN CHITIETDATPHONG ct ON ct.MAPHONG = p.MAPHONG "
                + "INNER JOIN HOPDONG h ON h.MAHOPDONG = ct.MAHOPDONG "
                + "WHERE (H.TGNHANPHONG < TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS') AND H.TGTRAPHONG >= TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS')) " // Thêm điều kiện này để bao gồm cả ngày 15
                + "OR (H.TGNHANPHONG = TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS') AND H.TGTRAPHONG > TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS')))"; // Thêm điều kiện này để bao gồm cả ngày 15

        try {
            Connection con = null;
            con = JDBCUtil.getConnection();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, loaiphong);
            ps.setString(2, ngayTra);
            ps.setString(3, ngayNhan);
            ps.setString(4, ngayTra);
            ps.setString(5, ngayNhan);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DS_Phong.add(new PhongModel(
                        rs.getInt("MaPhong"),
                        rs.getString("LoaiPhong"),
                        rs.getString("KieuPhong"),
                        rs.getLong("Gia")
                ));

            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DS_Phong;
    }

    public static ArrayList<PhongModel> hungia_getDStheoKieuPhong(String kieuphong, String ngayNhan, String ngayTra) {
        ArrayList<PhongModel> DS_Phong = new ArrayList<>();

        try {
            Connection con = null;
            con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM PHONG WHERE KIEUPHONG = ? AND MAPHONG NOT IN (SELECT p.MAPHONG FROM PHONG p "
                    + "INNER JOIN CHITIETDATPHONG ct ON ct.MAPHONG = p.MAPHONG "
                    + "INNER JOIN HOPDONG h ON h.MAHOPDONG = ct.MAHOPDONG "
                    + "WHERE (H.TGNHANPHONG < TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS') AND H.TGTRAPHONG >= TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS')) " // Thêm điều kiện này để bao gồm cả ngày 15
                    + "OR (H.TGNHANPHONG = TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS') AND H.TGTRAPHONG > TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS')))"; // Thêm điều kiện này để bao gồm cả ngày 15

            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, kieuphong);
            ps.setString(2, ngayTra);
            ps.setString(3, ngayNhan);
            ps.setString(4, ngayTra);
            ps.setString(5, ngayNhan);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DS_Phong.add(new PhongModel(
                        rs.getInt("MaPhong"),
                        rs.getString("LoaiPhong"),
                        rs.getString("KieuPhong"),
                        rs.getLong("Gia")
                ));

            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DS_Phong;
    }

    public static ArrayList<PhongModel> hungia_getDStheotracuu(String loaiphong, String kieuphong, String ngayNhan, String ngayTra) {
        ArrayList<PhongModel> DS_Phong = new ArrayList<>();
        String sql = "SELECT * FROM PHONG WHERE LOAIPHONG = ? AND KIEUPHONG = ? AND MAPHONG NOT IN (SELECT p.MAPHONG FROM PHONG p "
                + "INNER JOIN CHITIETDATPHONG ct ON ct.MAPHONG = p.MAPHONG "
                + "INNER JOIN HOPDONG h ON h.MAHOPDONG = ct.MAHOPDONG "
                + "WHERE (H.TGNHANPHONG < TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS') AND H.TGTRAPHONG >= TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS')) " // Thêm điều kiện này để bao gồm cả ngày 15
                + "OR (H.TGNHANPHONG = TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS') AND H.TGTRAPHONG > TO_DATE(?, 'DD-MM-YYYY HH24:MI:SS')))"; // Thêm điều kiện này để bao gồm cả ngày 15

        try {
            Connection con = null;
            con = JDBCUtil.getConnection();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, loaiphong);
            ps.setString(2, kieuphong);
            ps.setString(3, ngayTra);
            ps.setString(4, ngayNhan);
            ps.setString(5, ngayTra);
            ps.setString(6, ngayNhan);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DS_Phong.add(new PhongModel(
                        rs.getInt("MaPhong"),
                        rs.getString("LoaiPhong"),
                        rs.getString("KieuPhong"),
                        rs.getLong("Gia")
                ));

            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DS_Phong;
    }

    public static PhongModel getPhongTheoMa(int maPhong) throws SQLException {
        // Tạo câu truy vấn
        String sql = "SELECT * FROM PHONG WHERE MAPHONG=?";

        // Tạo đối tượng connection
        PhongModel p = null;

        try (Connection conn = JDBCUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maPhong);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String loaiPhong = rs.getString("LoaiPhong");
                    String kieuPhong = rs.getString("KieuPhong");
                    long gia = rs.getLong("Gia");

                    // gọi constructor
                    p = new PhongModel(maPhong, loaiPhong, kieuPhong, gia);
                }
            }
        } catch (SQLException ex) {
            // Xử lý ngoại lệ nếu cần, ví dụ: log lỗi
            ex.printStackTrace();
        }

        return p;
    }

    public static ArrayList<PhongModel> ttun_getDStheoThoiGian(String checkInDateTimeStr, String checkOutDateTimeStr) {
        ArrayList<PhongModel> DS_PHONG = new ArrayList<>();
        String sql = "SELECT * FROM PHONG WHERE MAPHONG NOT IN (SELECT p.MAPHONG FROM PHONG p "
                + "INNER JOIN CHITIETDATPHONG ct ON ct.MAPHONG = p.MAPHONG "
                + "INNER JOIN HOPDONG h ON h.MAHOPDONG = ct.MAHOPDONG "
                + "WHERE (H.TGNHANPHONG < TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS') AND H.TGTRAPHONG >= TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS')) " // Thêm điều kiện này để bao gồm cả ngày 15
                + "OR (H.TGNHANPHONG = TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS') AND H.TGTRAPHONG > TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS')))"; // Thêm điều kiện này để bao gồm cả ngày 15

        try {
            Connection con = JDBCUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, checkOutDateTimeStr);
            ps.setString(2, checkInDateTimeStr);
            ps.setString(3, checkOutDateTimeStr);
            ps.setString(4, checkInDateTimeStr);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DS_PHONG.add(new PhongModel(
                        rs.getInt("MaPhong"),
                        rs.getString("LoaiPhong"),
                        rs.getString("KieuPhong"),
                        rs.getLong("Gia")
                ));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DS_PHONG;
    }

    public static ArrayList<PhongModel> ttun_getDStheoLoaiPhong(String loaiphong, String checkInDateTimeStr, String checkOutDateTimeStr) {
        ArrayList<PhongModel> DS_PHONG = new ArrayList<>();
        String sql = "SELECT * FROM PHONG WHERE LOAIPHONG = ? AND MAPHONG NOT IN (SELECT p.MAPHONG FROM PHONG p "
                + "INNER JOIN CHITIETDATPHONG ct ON ct.MAPHONG = p.MAPHONG "
                + "INNER JOIN HOPDONG h ON h.MAHOPDONG = ct.MAHOPDONG "
                + "WHERE (H.TGNHANPHONG < TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS') AND H.TGTRAPHONG >= TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS')) " // Thêm điều kiện này để bao gồm cả ngày 15
                + "OR (H.TGNHANPHONG = TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS') AND H.TGTRAPHONG > TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS')))"; // Thêm điều kiện này để bao gồm cả ngày 15        
        try {
            Connection con;
            con = JDBCUtil.getConnection();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, loaiphong);
            ps.setString(2, checkOutDateTimeStr);
            ps.setString(3, checkInDateTimeStr);
            ps.setString(4, checkOutDateTimeStr);
            ps.setString(5, checkInDateTimeStr);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DS_PHONG.add(new PhongModel(
                        rs.getInt("MaPhong"),
                        rs.getString("LoaiPhong"),
                        rs.getString("KieuPhong"),
                        rs.getLong("Gia")
                ));

            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DS_PHONG;
    }

    public static ArrayList<PhongModel> ttun_getDStheoKieuPhong(String kieuphong, String checkInDateTimeStr, String checkOutDateTimeStr) {
        ArrayList<PhongModel> DS_PHONG = new ArrayList<>();

        try {
            Connection con;
            con = JDBCUtil.getConnection();

            String sql = "SELECT * FROM PHONG WHERE KIEUPHONG = ? AND MAPHONG NOT IN (SELECT p.MAPHONG FROM PHONG p "
                    + "INNER JOIN CHITIETDATPHONG ct ON ct.MAPHONG = p.MAPHONG "
                    + "INNER JOIN HOPDONG h ON h.MAHOPDONG = ct.MAHOPDONG "
                    + "WHERE (H.TGNHANPHONG < TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS') AND H.TGTRAPHONG >= TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS')) " // Thêm điều kiện này để bao gồm cả ngày 15
                    + "OR (H.TGNHANPHONG = TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS') AND H.TGTRAPHONG > TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS')))"; // Thêm điều kiện này để bao gồm cả ngày 15        
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, kieuphong);
            ps.setString(2, checkOutDateTimeStr);
            ps.setString(3, checkInDateTimeStr);
            ps.setString(4, checkOutDateTimeStr);
            ps.setString(5, checkInDateTimeStr);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DS_PHONG.add(new PhongModel(
                        rs.getInt("MaPhong"),
                        rs.getString("LoaiPhong"),
                        rs.getString("KieuPhong"),
                        rs.getLong("Gia")
                ));

            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DS_PHONG;
    }

    public static ArrayList<PhongModel> ttun_getDStheoTraCuu(String loaiphong, String kieuphong, String checkInDateTimeStr, String checkOutDateTimeStr) {
        ArrayList<PhongModel> DS_PHONG = new ArrayList<>();
        String sql = "SELECT * FROM PHONG WHERE LOAIPHONG = ? AND KIEUPHONG = ? AND MAPHONG NOT IN (SELECT p.MAPHONG FROM PHONG p "
                + "INNER JOIN CHITIETDATPHONG ct ON ct.MAPHONG = p.MAPHONG "
                + "INNER JOIN HOPDONG h ON h.MAHOPDONG = ct.MAHOPDONG "
                + "WHERE (H.TGNHANPHONG < TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS') AND H.TGTRAPHONG >= TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS')) " // Thêm điều kiện này để bao gồm cả ngày 15
                + "OR (H.TGNHANPHONG = TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS') AND H.TGTRAPHONG > TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS')))"; // Thêm điều kiện này để bao gồm cả ngày 15        
        try {
            Connection con;
            con = JDBCUtil.getConnection();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, loaiphong);
            ps.setString(2, kieuphong);
            ps.setString(3, checkOutDateTimeStr);
            ps.setString(4, checkInDateTimeStr);
            ps.setString(5, checkOutDateTimeStr);
            ps.setString(6, checkInDateTimeStr);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DS_PHONG.add(new PhongModel(
                        rs.getInt("MaPhong"),
                        rs.getString("LoaiPhong"),
                        rs.getString("KieuPhong"),
                        rs.getLong("Gia")
                ));

            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DS_PHONG;
    }

}
