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

    public static int ThemCTDP(int maHopDong, int maPhong) throws SQLException {
        Connection con = null;

        try {
            // Tạo kết nối đến cơ sở dữ liệu
            con = JDBCUtil.getConnection();

            // Thực thi câu lệnh SQL
            String sql = "INSERT INTO CHITIETDATPHONG (MAHOPDONG, MAPHONG) VALUES (?, ?)";

            // Tạo đối tượng PreparedStatement
            PreparedStatement ps = con.prepareStatement(sql);

            // Thiết lập các tham số cho câu lệnh SQL
            ps.setInt(1, maHopDong);
            ps.setInt(2, maPhong);

            // Thực thi câu lệnh SQL
            return ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi kết nối cơ sở dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return 0;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
    
    public static ArrayList<Vector> getCTDP(int maHopDong){
        // Tạo cái ArrayList lưu danh sách khách hàng
        ArrayList<Vector> DS_CTDP = new ArrayList<>();
        
        ArrayList<Vector> cthdList = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "SELECT PHONG.MaPhong, PHONG.Gia, HOPDONG.HinhThucThue, HOPDONG.TGNhanPhong, HOPDONG.TGTraPhong "
                    + "FROM HOPDONG "
                    + "JOIN CHITIETDATPHONG ON HOPDONG.MaHopDong = CHITIETDATPHONG.MaHopDong "
                    + "JOIN PHONG ON CHITIETDATPHONG.MaPhong = PHONG.MaPhong "
                    + "WHERE HOPDONG.MaHopDong = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, maHopDong);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vector cthd = new Vector<>();
                HopDongModel hopdong = HopDongDAO.getHDtheoMaHopDong(maHopDong);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String ngayden = hopdong.getTGNhanPhong().format(formatter);
                String ngaydi = hopdong.getTGTraPhong().format(formatter);
                long sogio = tinhThoiGian(ngayden, ngaydi) / 60;
                long songay = tinhKhoangCach2Ngay(ngayden, ngaydi);
                System.out.println(songay);
                double tongTienThue = 0;
                if ("Ngày".equals(rs.getString("HinhThucThue"))) {
                    tongTienThue = songay * rs.getDouble("Gia"); // Thuê theo ngày
                } else if ("Giờ".equals(rs.getString("HinhThucThue"))) {
                    tongTienThue = (sogio / 22.0) * rs.getDouble("Gia") * 1.5; // Thuê theo giờ
                }
                tongTienThue = tongTienThue * 30 /100;
                String formattedTongTienThue = String.format("%,.0f VND", tongTienThue);
                cthd.add(rs.getInt("MaPhong"));
                cthd.add(rs.getString("HinhThucThue"));
                java.sql.Date ngaynp = rs.getDate("TGNhanPhong");
                String formattedDatenp = (ngaynp != null) ? ngaynp.toLocalDate().format(dateFormatter) : null;
                java.sql.Date ngaytp = rs.getDate("TGTraPhong");
                String formattedDatetp = (ngaytp != null) ? ngaytp.toLocalDate().format(dateFormatter) : null;
                cthd.add(formattedDatenp);
                cthd.add(formattedDatetp);
                cthd.add(formattedTongTienThue);
                cthdList.add(cthd);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cthdList;
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
