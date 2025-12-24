package DAO;

import Connection.JDBCUtil;
import Model.ChiTietDatPhongModel;
import Model.HopDongModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Vector;

public class ChiTietDatPhongDAO {

    public static int ThemCTDP(int maHopDong, int maPhong, int soKhach) throws SQLException {
        Connection con = null;

        try {
            con = JDBCUtil.getConnection();

            String sql =
                "INSERT INTO CHITIETDATPHONG (MAHOPDONG, MAPHONG, SOKHACH) " +
                "VALUES (?, ?, ?)";


            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, maHopDong);
            ps.setInt(2, maPhong);
            ps.setInt(3, soKhach);

            return ps.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                null,
                "Lỗi thêm chi tiết đặt phòng: " + ex.getMessage(),
                "Lỗi",
                JOptionPane.ERROR_MESSAGE
            );
            return 0;
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }
    
    public static int XoaCTHDTheoMaHD(int maHDong) throws SQLException {
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        String sql = "DELETE CHITIETDATPHONG WHERE MAHOPDONG=?";
        PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
        ps.setInt(1, maHDong);
        
        
        return ps.executeUpdate();
    }
    
    public static ArrayList<Vector> getCTDP(int maHopDong) {
        ArrayList<Vector> cthdList = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String sql = """
            SELECT 
                P.MaPhong,
                C.SoKhach,
                L.Gia AS GIA,
                H.HinhThucThue,
                H.TGNhanPhong,
                H.TGTraPhong
            FROM HOPDONG H
            JOIN CHITIETDATPHONG C ON H.MaHopDong = C.MaHopDong
            JOIN PHONG P ON C.MaPhong = P.MaPhong
            JOIN LOAIPHONG L ON P.MaLoai = L.MaLoai
            WHERE H.MaHopDong = ?
        """;

        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maHopDong);

            // Lấy hợp đồng 1 lần
            HopDongModel hopdong = HopDongDAO.getHDtheoMaHopDong(maHopDong);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String ngayden = hopdong.getTGNhanPhong().format(formatter);
            String ngaydi  = hopdong.getTGTraPhong().format(formatter);

            long sogio = tinhThoiGian(ngayden, ngaydi) / 60;

            // Tính số ngày chuẩn (không dùng LocalDate.parse với format có giờ)
            long songay = tinhSoNgayTuDateTime(ngayden, ngaydi);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Vector cthd = new Vector();

                    double gia = rs.getDouble("GIA");
                    String hinhThuc = rs.getString("HinhThucThue");

                    double tongTienThue = 0;
                    if ("Ngày".equalsIgnoreCase(hinhThuc)) {
                        tongTienThue = songay * gia;
                    } else if ("Giờ".equalsIgnoreCase(hinhThuc)) {
                        tongTienThue = (sogio / 22.0) * gia * 1.5;
                    }

                    // tiền cọc 30%
                    tongTienThue = tongTienThue * 30 / 100.0;
                    String formattedTongTienThue = String.format("%,.0f VND", tongTienThue);

                    cthd.add(rs.getInt("MaPhong"));
                    cthd.add(rs.getInt("SoKhach"));    
                    cthd.add(hinhThuc);

                    // TIMESTAMP -> lấy LocalDate để format
                    java.sql.Timestamp tsNhan = rs.getTimestamp("TGNhanPhong");
                    java.sql.Timestamp tsTra  = rs.getTimestamp("TGTraPhong");

                    String formattedDatenp = (tsNhan != null)
                            ? tsNhan.toLocalDateTime().toLocalDate().format(dateFormatter)
                            : null;

                    String formattedDatetp = (tsTra != null)
                            ? tsTra.toLocalDateTime().toLocalDate().format(dateFormatter)
                            : null;

                    cthd.add(formattedDatenp);
                    cthd.add(formattedDatetp);
                    cthd.add(formattedTongTienThue);

                    cthdList.add(cthd);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cthdList;
    }

    // Tính số ngày từ 2 chuỗi datetime (dd-MM-yyyy HH:mm:ss)
    private static long tinhSoNgayTuDateTime(String startDateTime, String endDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse(startDateTime, formatter);
        LocalDateTime end   = LocalDateTime.parse(endDateTime, formatter);

        // lấy chênh lệch theo ngày dựa trên LocalDate
        return ChronoUnit.DAYS.between(start.toLocalDate(), end.toLocalDate());
    }

    
    public static long tinhThoiGian(String checkInDateTime, String checkOutDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime checkIn = LocalDateTime.parse(checkInDateTime, formatter);
        LocalDateTime checkOut = LocalDateTime.parse(checkOutDateTime, formatter);

        long khoangThoiGianGiuaHaiThoiDiem = ChronoUnit.MINUTES.between(checkIn, checkOut);

        return khoangThoiGianGiuaHaiThoiDiem;
    }

    public static long tinhKhoangCach2Ngay(String startDateStr, String endDateStr) {
        // Định dạng ngày
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Chuyển chuỗi ngày sang LocalDate
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);

        // Tính khoảng cách giữa hai ngày
        return ChronoUnit.DAYS.between(startDate, endDate);
    }
    
}
