package DAO;

import Connection.JDBCUtil;
import Model.HopDongModel;
import Model.KhachHangModel;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import javax.swing.JOptionPane;

public class HopDongDAO {

    // lấy danh sách khách hàng có trong cơ sở dữ liệu
    public static ArrayList<HopDongModel> getDSHopDong() {

        // Tạo cái ArrayList lưu danh sách khách hàng
        ArrayList<HopDongModel> DS_HopDong = new ArrayList<>();
        try {

            // Tạo câu truy vấn 
            String sql = "SELECT * FROM HOPDONG ORDER BY MaHopDong DESC";

            // Tạo đối tượng connection
            Connection conn = null;
            conn = JDBCUtil.getConnection();

            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            DS_HopDong.clear();

            while (rs.next()) {
                int MaHopDong = rs.getInt(1);
                int MaKH = rs.getInt(2);
                LocalDateTime NgayLapHopDong = rs.getTimestamp(3).toLocalDateTime();
                LocalDateTime TGNhanPhong = rs.getTimestamp(4).toLocalDateTime();
                LocalDateTime TGTraPhong = rs.getTimestamp(5).toLocalDateTime();
                String TinhTrangHD = rs.getString(6);
                int SoNguoiLon = rs.getInt(7);
                int SoTreEm = rs.getInt(8);
                double TriGiaHopDong = rs.getDouble(9);
                String HinhThucThue = rs.getString(10);

                // gọi contructer
                HopDongModel hopDong = new HopDongModel(MaHopDong, MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, TinhTrangHD, SoNguoiLon, SoTreEm, TriGiaHopDong, HinhThucThue);
                DS_HopDong.add(hopDong);
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return DS_HopDong;
    }

    public static ArrayList<HopDongModel> getHopDongTheoTinhTrang(String textInput) {
        ArrayList<HopDongModel> DS_HopDong = new ArrayList<>();
        try {
            Connection con = null;
            con = JDBCUtil.getConnection();
            String sql = null;

            sql = "SELECT * FROM HOPDONG WHERE TinhTrangHD = ?";

            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, textInput);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HopDongModel hopdong_temp = new HopDongModel();
                hopdong_temp.setMaHopDong(rs.getInt("MAHOPDONG"));
                hopdong_temp.setMaKH(rs.getInt("MAKH"));
                hopdong_temp.setNgayLapHopDong(rs.getTimestamp("NGAYLAPHOPDONG").toLocalDateTime());
                hopdong_temp.setTGNhanPhong(rs.getTimestamp("TGNHANPHONG").toLocalDateTime());
                hopdong_temp.setTGTraPhong(rs.getTimestamp("TGTRAPHONG").toLocalDateTime());
                hopdong_temp.setTinhTrangHD(rs.getString("TINHTRANGHD"));
                hopdong_temp.setSoLuongNguoiLon(rs.getInt("SONGUOILON"));
                hopdong_temp.setSoLuongTreEm(rs.getInt("SOTREEM"));
                hopdong_temp.setTriGiaHopDong(rs.getDouble("TRIGIAHD"));
                hopdong_temp.setHinhThucThue("HINHTHUCTHUE");
                DS_HopDong.add(hopdong_temp);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return DS_HopDong;
    }

    public static ArrayList<HopDongModel> TimHopDong(String option, String textInput) {
        ArrayList<HopDongModel> DS_HopDong = new ArrayList<>();
        try {
            Connection con = null;
            con = JDBCUtil.getConnection();
            String sql = null;
            switch (option) {
                case "Mã Hợp Đồng":
                    sql = "SELECT * FROM HOPDONG WHERE LOWER(MAHOPDONG) LIKE LOWER(?)";
                    textInput = "%" + textInput + "%"; // Để tìm theo chuỗi con
                    break;
                case "Mã KH":
                    sql = "SELECT * FROM HOPDONG WHERE LOWER(MAKH) LIKE LOWER(?)";
                    textInput = "%" + textInput + "%"; // Để tìm theo chuỗi con
                    break;
                case "Tình Trạng HĐ":
                    sql = "SELECT * FROM HOPDONG WHERE LOWER(TINHTRANGHD) LIKE LOWER(?)";
                    textInput = "%" + textInput + "%"; // Để tìm theo chuỗi con
                    break;
            }

            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, textInput);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HopDongModel hopdong_temp = new HopDongModel();
                hopdong_temp.setMaHopDong(rs.getInt("MAHOPDONG"));
                hopdong_temp.setMaKH(rs.getInt("MAKH"));
                hopdong_temp.setNgayLapHopDong(rs.getTimestamp("NGAYLAPHOPDONG").toLocalDateTime());
                hopdong_temp.setTGNhanPhong(rs.getTimestamp("TGNHANPHONG").toLocalDateTime());
                hopdong_temp.setTGTraPhong(rs.getTimestamp("TGTRAPHONG").toLocalDateTime());
                hopdong_temp.setTinhTrangHD(rs.getString("TINHTRANGHD"));
                hopdong_temp.setSoLuongNguoiLon(rs.getInt("SONGUOILON"));
                hopdong_temp.setSoLuongTreEm(rs.getInt("SOTREEM"));
                hopdong_temp.setTriGiaHopDong(rs.getDouble("TRIGIAHD"));
                hopdong_temp.setHinhThucThue("HINHTHUCTHUE");
                DS_HopDong.add(hopdong_temp);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return DS_HopDong;
    }

    public static int XoaHopDong(int maHopDong) throws SQLException {
        Connection con = null;
        try {
            con = JDBCUtil.getConnection();
            String sql = "DELETE HOPDONG WHERE MAHOPDONG=?";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, maHopDong);

            return ps.executeUpdate();
        } catch (SQLException ex) {
            if (ex.getMessage().contains("ORA-02292; integrity constraint (DATABASE_DOAN_NPNT.FK_HOADON HOPDONG) violated - child record found https://docs.oracle.com/error-help/db/ora-02292/")) {
                JOptionPane.showMessageDialog(null, "Lỗi: Hợp đồng này đã thuộc một hóa đơn, không thể xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else if (ex.getMessage().contains("DATABASE_DOAN_NPNT.FK_HOADON HOPDONG")) {
                JOptionPane.showMessageDialog(null, "Lỗi: Hợp đồng này đã thuộc một hóa đơn, không thể xóa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi: Hợp đồng này đã thuộc một hóa đơn, không thể xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            return 0;
        }
    }

    public static HopDongModel getHDtheoMaHopDong(int maHopDong) throws SQLException {
        // Tạo câu truy vấn 
        String sql = "SELECT * FROM HOPDONG WHERE MaHopDong=?";

        // Tạo đối tượng connection
        Connection conn = null;
        HopDongModel hopDong = null;

        try {
            conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, maHopDong);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int MaKH = rs.getInt(2);
                LocalDateTime NgayLapHopDong = rs.getTimestamp(3).toLocalDateTime();
                LocalDateTime TGNhanPhong = rs.getTimestamp(4).toLocalDateTime();
                LocalDateTime TGTraPhong = rs.getTimestamp(5).toLocalDateTime();
                String TinhTrangHD = rs.getString(6);
                int SoNguoiLon = rs.getInt(7);
                int SoTreEm = rs.getInt(8);
                double TriGiaHopDong = rs.getDouble(9);
                String HinhThucThue = rs.getString(10);

                // gọi constructor
                hopDong = new HopDongModel(MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, TinhTrangHD, SoNguoiLon, SoTreEm, TriGiaHopDong, HinhThucThue);
            }

            rs.close();
            ps.close();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return hopDong;
    }

    public static boolean CapNhatHopDong(int maHopDong, HopDongModel hopDong) throws SQLException {
        String sql = "UPDATE HOPDONG SET SONGUOILON = ?, SOTREEM = ?, TINHTRANGHD = ? WHERE MAHOPDONG = ?";

        try (Connection con = JDBCUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, hopDong.getSoLuongNguoiLon());
            ps.setInt(2, hopDong.getSoLuongTreEm());
            ps.setString(3, hopDong.getTinhTrangHD());
            ps.setInt(4, maHopDong);

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static int ThemCTDP(Connection con, int maHopDong, int maPhong) throws SQLException {
        String sql = "INSERT INTO CHITIETDATPHONG (MAHOPDONG, MAPHONG) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maHopDong);
            ps.setInt(2, maPhong);
            return ps.executeUpdate();
        }
    }

    public int hungia_ThemHopDongVaCTDP(int makh, String ngayNhan, String ngayTra, int soNL, int soTE, long tien, List<Integer> soPhongDatList) throws SQLException {
        Connection con = null;

        try {
            // Tạo kết nối đến cơ sở dữ liệu
            con = JDBCUtil.getConnection();
            // Bắt đầu một giao dịch
            con.setAutoCommit(false);

            // Thực thi câu lệnh SQL để chèn vào HOPDONG
            String sqlHopDong = "INSERT INTO HOPDONG (MaHopDong, MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, TinhTrangHD, SoNguoiLon, SoTreEm, TriGiaHD, HinhThucThue) "
                    + "VALUES (HopDong_Seq.NEXTVAL, ?, SYSTIMESTAMP, TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS'), 'Chưa xác nhận', ?, ?, ?, 'Ngày')";

            try (PreparedStatement psHopDong = con.prepareStatement(sqlHopDong, new String[]{"MaHopDong"})) {
                psHopDong.setInt(1, makh);
                psHopDong.setString(2, ngayNhan);
                psHopDong.setString(3, ngayTra);
                psHopDong.setInt(4, soNL);
                psHopDong.setInt(5, soTE);
                psHopDong.setLong(6, tien);

                // Thực thi câu lệnh SQL cho HOPDONG
                int row = psHopDong.executeUpdate();
                if (row <= 0) {
                    con.rollback();
                    JOptionPane.showMessageDialog(null, "Không thể chèn hợp đồng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return 0;
                }

                // Lấy MaHopDong vừa được tạo
                ResultSet rs = psHopDong.getGeneratedKeys();
                int maHopDong = 0;
                if (rs.next()) {
                    maHopDong = rs.getInt(1);
                } else {
                    con.rollback();
                    JOptionPane.showMessageDialog(null, "Không thể lấy mã hợp đồng mới.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return 0;
                }

                // Thực thi câu lệnh SQL để chèn vào CHITIETDATPHONG
                for (int maPhong : soPhongDatList) {
                    int row1 = ThemCTDP(con, maHopDong, maPhong);
                    if (row1 <= 0) {
                        con.rollback();
                        JOptionPane.showMessageDialog(null, "Không thể thêm phòng " + maPhong + " vào hợp đồng " + maHopDong, "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return 0;
                    }
                    System.out.println("Thêm thành công phòng " + maPhong + " vào hợp đồng " + maHopDong);
                }

                // Commit giao dịch nếu cả hai thao tác đều thành công
                con.commit();
                return 1;
            }

        } catch (SQLException ex) {
            // Rollback giao dịch nếu có lỗi
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // Hiển thị thông báo lỗi chi tiết
            JOptionPane.showMessageDialog(null, "Lỗi SQL: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
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

    public static int getMaHopDongMoiNhat() {
        // Khai báo biến MaHopDong với giá trị mặc định
        int maHopDong = 0;

        // Sử dụng try-with-resources để tự động quản lý tài nguyên
        try (
                Connection con = JDBCUtil.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT MAX(MAHOPDONG) FROM HOPDONG"); ResultSet rs = ps.executeQuery()) {
            // Kiểm tra kết quả trả về từ truy vấn
            if (rs.next()) {
                maHopDong = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Trả về kết quả
        return maHopDong;
    }

// Lấy giá phòng của (một hoặc nhiều) nằm trong hợp đồng đó
    public static Double tongGiaPhongNgay(int mahd, long soluong) {
        double tong = 0.0;
        String sql = "SELECT SUM(P.Gia) AS Tong "
                + "FROM PHONG P "
                + "JOIN CHITIETDATPHONG CTDP ON P.MaPhong = CTDP.MaPhong "
                + "JOIN HOPDONG HD ON HD.MaHopDong = CTDP.MaHopDong "
                + "WHERE HD.MaHopDong = ?";

        try (Connection con = JDBCUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, mahd);
            System.out.println("Executing query with mahd: " + mahd);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tong = rs.getDouble("Tong") * soluong;
                    System.out.println("Gia phong tong cong: " + rs.getDouble("Tong"));
                    System.out.println("So luong: " + soluong);
                    System.out.println("Tong gia phong ngay: " + tong);
                } else {
                    System.out.println("No data found for mahd: " + mahd);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tong;
    }

    // Cập nhật tình trạng hợp đồng sang đã thanh toán
    public static void capnhatTinhTrang(int mahd) {
        String sql = "UPDATE HOPDONG SET TinhTrangHD = 'Đã thanh toán' WHERE MaHopDong = ?";

        try (Connection con = JDBCUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, mahd);
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Lấy giá phòng của (một hoặc nhiều) nằm trong hợp đồng đó
    public static Double tongGiaPhongGio(int mahd, long soluong) {
        double tong = 0.0;
        String sql = "SELECT SUM(P.Gia) AS Tong "
                + "FROM PHONG P "
                + "JOIN CHITIETDATPHONG CTDP ON P.MaPhong = CTDP.MaPhong "
                + "JOIN HOPDONG HD ON HD.MaHopDong = CTDP.MaHopDong "
                + "WHERE HD.MaHopDong = ?";

        try (Connection con = JDBCUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, mahd);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    double giaTheoGio = rs.getDouble("Tong") / 22.0 * 1.5;
                    tong = giaTheoGio * soluong;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tong;
    }

    public int ttunThemHopDong(int makh, String checkInDateTime, String checkOutDateTime, int soNL, int soTE) throws SQLException {

        Connection con = null;

        try {
            // Tạo kết nối đến cơ sở dữ liệu
            con = JDBCUtil.getConnection();

            // Thực thi câu lệnh SQL
            String sql = "INSERT INTO HOPDONG (MaHopDong, MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, TinhTrangHD, SoNguoiLon, SoTreEm, TriGiaHD, HinhThucThue) "
                    + "VALUES (HopDong_Seq.NEXTVAL, ?, SYSTIMESTAMP, TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS'), TO_TIMESTAMP(?, 'DD-MM-YYYY HH24:MI:SS'), 'Đã xác nhận', ?, ?, 0, 'Giờ')";

            // Tạo đối tượng PreparedStatement
            PreparedStatement ps = con.prepareStatement(sql);

            // Thiết lập các tham số cho câu lệnh SQL
            ps.setInt(1, makh);
            ps.setString(2, checkInDateTime);
            ps.setString(3, checkOutDateTime);
            ps.setInt(4, soNL);
            ps.setInt(5, soTE);

            // Thực thi câu lệnh SQL
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Lỗi SQL: " + ex.getMessage());
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

    public static long tinhThoiGian(String checkInDateTime, String checkOutDateTime) {
        // Định dạng cho ngày giờ
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        // Chuyển đổi chuỗi thành LocalDateTime
        LocalDateTime checkIn = LocalDateTime.parse(checkInDateTime, formatter);
        LocalDateTime checkOut = LocalDateTime.parse(checkOutDateTime, formatter);

        // Tính khoảng thời gian giữa hai thời điểm
        long khoangThoiGianGiuaHaiThoiDiem = ChronoUnit.MINUTES.between(checkIn, checkOut);

        return khoangThoiGianGiuaHaiThoiDiem;
    }

    public static HopDongModel getHopDongTheoMaHP(int maHopDong) {
        Connection con = null;
        try {
            String sql = "SELECT * FROM HOPDONG WHERE MAHOPDONG = ?";
            HopDongModel hopdong_temp = new HopDongModel();
            con = JDBCUtil.getConnection();

            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, maHopDong);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                hopdong_temp.setMaHopDong(rs.getInt("MAHOPDONG"));
                hopdong_temp.setMaKH(rs.getInt("MAKH"));
                hopdong_temp.setNgayLapHopDong(rs.getTimestamp("NGAYLAPHOPDONG").toLocalDateTime());
                hopdong_temp.setTGNhanPhong(rs.getTimestamp("TGNHANPHONG").toLocalDateTime());
                hopdong_temp.setTGTraPhong(rs.getTimestamp("TGTRAPHONG").toLocalDateTime());
                hopdong_temp.setTinhTrangHD(rs.getString("TINHTRANGHD"));
                hopdong_temp.setSoLuongNguoiLon(rs.getInt("SONGUOILON"));
                hopdong_temp.setSoLuongTreEm(rs.getInt("SOTREEM"));
                hopdong_temp.setTriGiaHopDong(rs.getDouble("TRIGIAHD"));
                hopdong_temp.setHinhThucThue("HINHTHUCTHUE");
            }
            con.close();
            return hopdong_temp;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static KhachHangModel getKHtheoMaHopDong(int maHopDong) throws SQLException {
        String sql = "SELECT k.* FROM HOPDONG h JOIN KHACHHANG k ON h.MAKH = k.MAKH WHERE MAHOPDONG = ?";
        // Tạo đối tượng connection
        Connection conn = null;
        KhachHangModel kh = null;

        try {
            conn = JDBCUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, maHopDong);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int maKH = rs.getInt(1);
                String TenKH = rs.getString(2);
                String CCCD = rs.getString(3);
                LocalDate NgaySinh = rs.getDate(4).toLocalDate();
                String GioiTinh = rs.getString(5);
                String DiaChi = rs.getString(6);
                String SDT = rs.getString(7);
                int SoHopDong = rs.getInt(8);

                // gọi constructor
                kh = new KhachHangModel(maKH, TenKH, CCCD, NgaySinh, GioiTinh, DiaChi, SDT, SoHopDong);
            }

            rs.close();
            ps.close();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return kh;
    }

    public static ArrayList<HopDongModel> TimHopDongTheoSoPhong(int sophong) {
        ArrayList<HopDongModel> DS_HopDong = new ArrayList<>();
        try {
            Connection con = null;
            con = JDBCUtil.getConnection();
            String sql = null;
            sql = "SELECT h.* FROM HOPDONG h "
                    + "JOIN CHITIETDATPHONG c ON H.MAHOPDONG = c.MAHOPDONG "
                    + "WHERE C.MAPHONG = ? "
                    + "ORDER BY H.TGTRAPHONG DESC ";

            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, sophong);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int MaHopDong = rs.getInt(1);
                int MaKH = rs.getInt(2);
                LocalDateTime NgayLapHopDong = rs.getTimestamp(3).toLocalDateTime();
                LocalDateTime TGNhanPhong = rs.getTimestamp(4).toLocalDateTime();
                LocalDateTime TGTraPhong = rs.getTimestamp(5).toLocalDateTime();
                String TinhTrangHD = rs.getString(6);
                int SoNguoiLon = rs.getInt(7);
                int SoTreEm = rs.getInt(8);
                double TriGiaHopDong = rs.getDouble(9);
                String HinhThucThue = rs.getString(10);

                // gọi contructer
                HopDongModel hopDong = new HopDongModel(MaHopDong, MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, TinhTrangHD, SoNguoiLon, SoTreEm, TriGiaHopDong, HinhThucThue);
                DS_HopDong.add(hopDong);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return DS_HopDong;
    }

    public static ArrayList<HopDongModel> TimHopDongTheoCCCD(String CCCD) {
        ArrayList<HopDongModel> DS_HopDong = new ArrayList<>();
        try {
            Connection con = null;
            con = JDBCUtil.getConnection();
            String sql = null;
            sql = "SELECT h.* FROM HOPDONG h "
                    + "JOIN KHACHHANG k ON H.MAKH = K.MAKH "
                    + "WHERE K.CCCD = ? "
                    + "ORDER BY H.TGNHANPHONG DESC ";

            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, CCCD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int MaHopDong = rs.getInt(1);
                int MaKH = rs.getInt(2);
                LocalDateTime NgayLapHopDong = rs.getTimestamp(3).toLocalDateTime();
                LocalDateTime TGNhanPhong = rs.getTimestamp(4).toLocalDateTime();
                LocalDateTime TGTraPhong = rs.getTimestamp(5).toLocalDateTime();
                String TinhTrangHD = rs.getString(6);
                int SoNguoiLon = rs.getInt(7);
                int SoTreEm = rs.getInt(8);
                double TriGiaHopDong = rs.getDouble(9);
                String HinhThucThue = rs.getString(10);

                // gọi contructer
                HopDongModel hopDong = new HopDongModel(MaHopDong, MaKH, NgayLapHopDong, TGNhanPhong, TGTraPhong, TinhTrangHD, SoNguoiLon, SoTreEm, TriGiaHopDong, HinhThucThue);
                DS_HopDong.add(hopDong);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return DS_HopDong;
    }

    public static ArrayList<Vector> getSoKHTrongThang() {
        ArrayList<Vector> DS_SoKH = new ArrayList<>();

        try {
            String sql = "SELECT SUM(SONGUOILON) AS tong_so_nguoi_lon, "
                    + "       EXTRACT(MONTH FROM TGNHANPHONG) AS thang, "
                    + "       EXTRACT(YEAR FROM TGNHANPHONG) AS nam "
                    + "FROM HOPDONG "
                    + "GROUP BY EXTRACT(MONTH FROM TGNHANPHONG), EXTRACT(YEAR FROM TGNHANPHONG) "
                    + "ORDER BY EXTRACT(YEAR FROM TGNHANPHONG), EXTRACT(MONTH FROM TGNHANPHONG)";

            Connection con = JDBCUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("tong_so_nguoi_lon"));
                row.add(rs.getInt("thang"));
                row.add(rs.getInt("nam"));
                DS_SoKH.add(row);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return DS_SoKH;
    }

    public static ArrayList<Vector> getHTThuePhong() {
        ArrayList<Vector> DS_HTThue = new ArrayList<>();

        try {
            String sql = "SELECT HINHTHUCTHUE, COUNT(H.MAHOPDONG) as so_Hop_dong, "
                    + "EXTRACT(MONTH FROM H.NGAYLAPHOPDONG) AS thang, "
                    + "EXTRACT(YEAR FROM H.NGAYLAPHOPDONG) AS nam "
                    + "FROM HOPDONG H "
                    + "GROUP BY H.HINHTHUCTHUE, EXTRACT(MONTH FROM H.NGAYLAPHOPDONG), EXTRACT(YEAR FROM H.NGAYLAPHOPDONG) "
                    + "ORDER BY EXTRACT(YEAR FROM H.NGAYLAPHOPDONG), EXTRACT(MONTH FROM H.NGAYLAPHOPDONG)";

            Connection con = JDBCUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getString("HINHTHUCTHUE"));
                row.add(rs.getInt("so_Hop_dong"));
                row.add(rs.getInt("thang"));
                row.add(rs.getInt("nam"));
                DS_HTThue.add(row);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return DS_HTThue;
    }

    //public static 
}
